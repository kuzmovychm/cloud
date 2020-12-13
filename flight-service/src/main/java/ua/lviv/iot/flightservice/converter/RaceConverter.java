package ua.lviv.iot.flightservice.converter;

import ua.lviv.iot.flightservice.dto.RaceDTO;
import ua.lviv.iot.flightservice.entity.race.Race;

import java.util.List;
import java.util.stream.Collectors;

public class RaceConverter {
    public static RaceDTO toDTO(Race race) {
        return RaceDTO.builder()
                .identifier(race.getIdentifier())
                .airline(AirlineConverter.toDTO(race.getAirline()))
                .landmarks(RaceLandmarkConverter.toDTO(race.getLandmarks()))
                .build();
    }

    public static List<RaceDTO> toDTO(List<Race> races) {
        return races.stream()
                .map(RaceConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static Race toEntity(RaceDTO raceDTO) {
        Race race = new Race();
        race.setAirline(AirlineConverter.toEntity(raceDTO.getAirline()));
        race.setIdentifier(raceDTO.getIdentifier());
        return race;
    }

}
