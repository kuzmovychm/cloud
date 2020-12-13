package ua.lviv.iot.flightservice.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.lviv.iot.flightservice.entity.Airline;
import ua.lviv.iot.flightservice.entity.Landmark;
import ua.lviv.iot.flightservice.entity.airplane.Airplane;
import ua.lviv.iot.flightservice.entity.airplane.AirplaneRace;
import ua.lviv.iot.flightservice.entity.race.Race;
import ua.lviv.iot.flightservice.entity.race.RaceLandmark;
import ua.lviv.iot.flightservice.repo.*;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static ua.lviv.iot.flightservice.initializer.DefaultData.*;

@Component
@RequiredArgsConstructor
public class DefaultInitializer {
    private final AirlineRepo airlineRepo;
    private final LandmarkRepo landmarkRepo;
    private final AirplaneRepo airplaneRepo;
    private final RaceRepo raceRepo;
    private final RaceLandmarkRepo raceLandmarkRepo;
    private final AirplaneRaceRepo airplaneRaceRepo;


    @PostConstruct
    public void init() {
        if (airlineRepo.count() == 0) {
            fillData();
        }
    }

    private void fillData() {
        Airline airline = airlineRepo.save(DEFAULT_AIRLINE);
        List<Landmark> landmarks = landmarkRepo.saveAll(DEFAULT_LANDMARKS);

        Airplane airplane = DEFAULT_AIRPLANE;
        airplane.setCurrentLocation(landmarks.get(0));
        airplaneRepo.save(airplane);
        addRace(airplane, airline, landmarks);
    }

    private void addRace(Airplane airplane, Airline airline, List<Landmark> landmarks) {
        Race race = create(airline, landmarks);
        AirplaneRace airplaneRace = new AirplaneRace();
        airplaneRace.setRace(race);
        airplaneRace.setIsActive(true);
        airplaneRace.setAirplane(airplane);
        airplaneRaceRepo.save(airplaneRace);
    }

    private Race create(Airline airline, List<Landmark> landmarks) {
        Race race = new Race();
        race.setIdentifier("ir-32-max-default-race");
        race.setAirline(airline);
        addLandmarks(raceRepo.save(race), landmarks);
        return race;
    }

    private void addLandmarks(Race race, List<Landmark> landmarks) {
        AtomicLong ordinalNumber = new AtomicLong(0);
        List<RaceLandmark> raceLandmarks = landmarks.stream()
                .map(landmark -> toRaceLandmark(race, landmark, ordinalNumber.incrementAndGet()))
                .collect(Collectors.toList());

        raceLandmarkRepo.saveAll(raceLandmarks);
    }

    private RaceLandmark toRaceLandmark(Race race, Landmark landmark, Long ordinalNumber) {
        RaceLandmark raceLandmark = new RaceLandmark();
        raceLandmark.setRace(race);
        raceLandmark.setLandmark(landmark);
        raceLandmark.setOrdinalNumber(ordinalNumber);
        return raceLandmark;
    }
}
