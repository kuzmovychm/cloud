package ua.lviv.iot.flightservice.service;

import ua.lviv.iot.flightservice.dto.RaceDTO;
import ua.lviv.iot.flightservice.entity.race.Race;

import java.util.List;

public interface RaceService {
    Race get(RaceDTO raceDTO);

    Race get(String identifier);

    Race create(RaceDTO raceDTO);

    Race update(RaceDTO raceDTO);

    List<Race> getAll();
}
