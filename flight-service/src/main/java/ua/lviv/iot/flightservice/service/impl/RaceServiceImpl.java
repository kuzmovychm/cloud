package ua.lviv.iot.flightservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.lviv.iot.flightservice.converter.LandmarkConverter;
import ua.lviv.iot.flightservice.converter.RaceConverter;
import ua.lviv.iot.flightservice.dto.RaceDTO;
import ua.lviv.iot.flightservice.entity.Landmark;
import ua.lviv.iot.flightservice.entity.race.Race;
import ua.lviv.iot.flightservice.entity.race.RaceLandmark;
import ua.lviv.iot.flightservice.exception.CustomException;
import ua.lviv.iot.flightservice.repo.RaceLandmarkRepo;
import ua.lviv.iot.flightservice.repo.RaceRepo;
import ua.lviv.iot.flightservice.service.LandmarkService;
import ua.lviv.iot.flightservice.service.RaceService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService {
    private final RaceRepo raceRepo;
    private final RaceLandmarkRepo raceLandmarkRepo;
    private final LandmarkService landmarkService;

    @Override
    public Race get(RaceDTO raceDTO) {
        Optional<Race> race = raceRepo.getWithLandmarks(raceDTO.getIdentifier());
        return race.orElseGet(() -> create(raceDTO));
    }

    @Override
    public Race get(String identifier) {
        return raceRepo.getWithLandmarks(identifier).orElseThrow(
                () -> new CustomException("No race with identifier: " + identifier));
    }

    @Override
    public Race create(RaceDTO raceDTO) {
        List<Landmark> landmarks = getLandmarks(raceDTO);
        return create(raceDTO, landmarks);
    }

    @Override
    public Race update(RaceDTO raceDTO) {
        Race race = raceRepo.getWithLandmarks(raceDTO.getIdentifier()).orElseThrow(
                () -> new CustomException("No race with identifier: " + raceDTO.getIdentifier()));

        List<Landmark> landmarks = CollectionUtils.isEmpty(raceDTO.getLandmarks())
                ? landmarkService.get(raceDTO.getOrderedIDs())
                : LandmarkConverter.toEntity(raceDTO.getLandmarks());

        return merge(race, landmarks);
    }

    @Override
    public List<Race> getAll() {
        return raceRepo.getWithLandmarks();
    }

    private Race merge(Race race, List<Landmark> current) {
        List<RaceLandmark> previous = race.getLandmarks();
        List<RaceLandmark> filteredPrevious = previous.stream()
                .filter(p -> {
                    boolean isPresent = current.contains(p.getLandmark());
                    if (!isPresent) raceLandmarkRepo.delete(p);
                    return isPresent;
                })
                .collect(Collectors.toList());

        Long max = getLastOrdinalNumber(filteredPrevious);

        AtomicLong atomicLong = new AtomicLong(max);
        List<RaceLandmark> newLandmarks = current.stream()
                .map(c -> {
                    if (isPresent(c, filteredPrevious)) return getPresent(c, filteredPrevious);
                    return create(race, c, atomicLong.incrementAndGet());
                }).collect(Collectors.toList());

        race.setLandmarks(newLandmarks);
        return raceRepo.save(race);
    }

    private RaceLandmark getPresent(Landmark c, List<RaceLandmark> filteredPrevious) {
        return filteredPrevious.stream()
                .filter(raceLandmark -> raceLandmark.getLandmark().equals(c))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Landmark is not in the list"));
    }

    private boolean isPresent(Landmark c, List<RaceLandmark> filteredPrevious) {
        return filteredPrevious.stream()
                .map(RaceLandmark::getLandmark)
                .collect(Collectors.toList())
                .contains(c);
    }

    private Long getLastOrdinalNumber(List<RaceLandmark> raceLandmarks) {
        return raceLandmarks.stream()
                .max(Comparator.comparingLong(RaceLandmark::getOrdinalNumber))
                .map(RaceLandmark::getOrdinalNumber)
                .orElse(0L);
    }

    private RaceLandmark create(Race race, Landmark landmark, Long ordinalNumber) {
        RaceLandmark raceLandmark = new RaceLandmark();
        raceLandmark.setRace(race);
        raceLandmark.setLandmark(landmarkService.save(landmark));
        raceLandmark.setOrdinalNumber(ordinalNumber);
        return raceLandmark;
    }

    private List<Landmark> getLandmarks(RaceDTO raceDTO) {
        if (CollectionUtils.isEmpty(raceDTO.getLandmarks())){
            return landmarkService.get(raceDTO.getOrderedIDs());
        }

        return landmarkService.save(LandmarkConverter.toEntity(raceDTO.getLandmarks()));
    }

    private Race create(RaceDTO raceDTO, List<Landmark> landmarks) {
        Race race = raceRepo.save(RaceConverter.toEntity(raceDTO));
        List<RaceLandmark> raceLandmarks = convert(race, landmarks);
        race.setLandmarks(raceLandmarks);
        return raceRepo.save(race);
    }

    private List<RaceLandmark> convert(Race race, List<Landmark> landmarks) {
        AtomicLong atomicLong = new AtomicLong(0);
        return landmarks.stream()
                .map(landmark -> {
                    RaceLandmark raceLandmark = new RaceLandmark();
                    raceLandmark.setRace(race);
                    raceLandmark.setLandmark(landmark);
                    raceLandmark.setOrdinalNumber(atomicLong.incrementAndGet());
                    return raceLandmark;
                }).collect(Collectors.toList());
    }

}
