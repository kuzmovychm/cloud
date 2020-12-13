package ua.lviv.iot.flightservice.converter;

import org.hibernate.LazyInitializationException;
import org.springframework.util.CollectionUtils;
import ua.lviv.iot.flightservice.dto.AirplaneDTO;
import ua.lviv.iot.flightservice.entity.airplane.Airplane;
import ua.lviv.iot.flightservice.entity.airplane.AirplaneRace;
import ua.lviv.iot.flightservice.entity.race.Race;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AirplaneConverter {
    public static AirplaneDTO toDTO(Airplane airplane) {
        AirplaneDTO dto = AirplaneDTO.builder()
                .id(airplane.getId())
                .name(airplane.getName())
                .identifier(airplane.getIdentifier())
                .speed(airplane.getSpeed())
                .kilometersPast(airplane.getKilometersPast())
                .currentLocation(LandmarkConverter.toDTO(airplane.getCurrentLocation()))
                .build();

        try {
            getCurrent(airplane.getRaces()).ifPresent(race -> dto.setCurrentRace(RaceConverter.toDTO(race)));
            dto.setPreviousRaces(RaceConverter.toDTO(getPrevious(airplane.getRaces())));
        } catch (LazyInitializationException e) {
            
        }
        return dto;
    }

    public static List<AirplaneDTO> toDTO(Collection<Airplane> airplanes) {
        return airplanes.stream()
                .map(AirplaneConverter::toDTO)
                .collect(Collectors.toList());
    }

    private static Optional<Race> getCurrent(List<AirplaneRace> races) {
        if (CollectionUtils.isEmpty(races)) return Optional.empty();

        return races.stream()
                .filter(AirplaneRace::getIsActive)
                .map(AirplaneRace::getRace)
                .findFirst();
    }

    private static List<Race> getPrevious(List<AirplaneRace> races) {
        if (CollectionUtils.isEmpty(races)) return Collections.EMPTY_LIST;

        return races.stream()
                .filter(airplaneRace -> !airplaneRace.getIsActive())
                .map(AirplaneRace::getRace)
                .collect(Collectors.toList());
    }

}
