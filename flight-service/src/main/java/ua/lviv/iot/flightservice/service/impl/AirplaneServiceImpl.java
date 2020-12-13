package ua.lviv.iot.flightservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.iot.flightservice.entity.airplane.Airplane;
import ua.lviv.iot.flightservice.entity.airplane.AirplaneRace;
import ua.lviv.iot.flightservice.entity.race.Race;
import ua.lviv.iot.flightservice.exception.CustomException;
import ua.lviv.iot.flightservice.repo.AirplaneRaceRepo;
import ua.lviv.iot.flightservice.repo.AirplaneRepo;
import ua.lviv.iot.flightservice.service.AirplaneService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepo airplaneRepo;
    private final AirplaneRaceRepo airplaneRaceRepo;

    @Override
    public List<Airplane> getAll() {
        return airplaneRepo.findAll();
    }

    @Override
    public Airplane getWithRaces(String identifier) {
        return airplaneRepo.getWithRaces(identifier).orElseThrow(() -> new CustomException("No airplane with identifier: " + identifier));
    }

    @Override
    public Optional<Race> getCurrent(String identifier) {
        return airplaneRaceRepo.getCurrent(identifier);
    }

    @Override
    @Transactional
    public Race add(Long id, Race race) {
        if (airplaneRaceRepo.existsByAirplaneIdAndRaceIdentifier(id, race.getIdentifier())) {
            throw new CustomException("Airplane with id: " + id + " already has such race: " + race.getIdentifier());
        }

        return save(id, race);
    }

    private Race save(Long id, Race race) {
        Airplane airplane = airplaneRepo.findById(id).orElseThrow(() -> new CustomException("No airplane with id: " + id));
        airplaneRaceRepo.setActiveFalse(airplane.getId());
        AirplaneRace airplaneRace = new AirplaneRace();
        airplaneRace.setRace(race);
        airplaneRace.setAirplane(airplane);
        airplaneRace.setIsActive(true);
        airplaneRaceRepo.save(airplaneRace);
        return race;
    }
}
