package ua.lviv.iot.flightservice.service;

import ua.lviv.iot.flightservice.entity.airplane.Airplane;
import ua.lviv.iot.flightservice.entity.race.Race;

import java.util.List;
import java.util.Optional;

public interface AirplaneService {
    List<Airplane> getAll();

    Airplane getWithRaces(String identifier);

    Optional<Race> getCurrent(String identifier);

    Race add(Long id, Race race);
}
