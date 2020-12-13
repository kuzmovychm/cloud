package ua.lviv.iot.flightservice.converter;

import ua.lviv.iot.flightservice.dto.LandmarkDTO;
import ua.lviv.iot.flightservice.entity.race.RaceLandmark;

import java.util.List;
import java.util.stream.Collectors;

public class RaceLandmarkConverter {
    public static List<LandmarkDTO> toDTO(List<RaceLandmark> landmarks) {
        return landmarks.stream()
                .map(raceLandmark -> {
                    LandmarkDTO dto = LandmarkConverter.toDTO(raceLandmark.getLandmark());
                    dto.setOrdinalNumber(raceLandmark.getOrdinalNumber());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
