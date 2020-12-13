package ua.lviv.iot.flightservice.converter;

import ua.lviv.iot.flightservice.dto.AirlineDTO;
import ua.lviv.iot.flightservice.entity.Airline;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AirlineConverter {
    public static AirlineDTO toDTO(Airline airline) {
        if (Objects.isNull(airline)) return null;

        return AirlineDTO.builder()
                .id(airline.getId())
                .name(airline.getName())
                .build();
    }

    public static List<AirlineDTO> toDTO(Collection<Airline> airlines) {
        return airlines.stream()
                .map(AirlineConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static Airline toEntity(AirlineDTO airlineDTO) {
        if (Objects.isNull(airlineDTO)) {
            return null;
        }

        Airline airline = new Airline();
        airline.setId(airlineDTO.getId());
        airline.setName(airlineDTO.getName());
        return airline;
    }
}
