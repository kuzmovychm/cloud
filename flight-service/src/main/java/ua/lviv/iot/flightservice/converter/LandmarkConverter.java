package ua.lviv.iot.flightservice.converter;

import ua.lviv.iot.flightservice.dto.LandmarkDTO;
import ua.lviv.iot.flightservice.entity.Landmark;

import java.util.List;
import java.util.stream.Collectors;

public class LandmarkConverter {
    public static LandmarkDTO toDTO(Landmark landmark) {
        return LandmarkDTO.builder()
                .id(landmark.getId())
                .name(landmark.getName())
                .latitude(landmark.getLatitude())
                .longitude(landmark.getLongitude())
                .build();
    }

    public static List<LandmarkDTO> toDTO(List<Landmark> landmarks) {
        return landmarks.stream()
                .map(LandmarkConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static Landmark toEntity(LandmarkDTO landmarkDTO) {
        Landmark landmark = new Landmark();
        landmark.setId(landmarkDTO.getId());
        landmark.setName(landmarkDTO.getName());
        landmark.setLatitude(landmarkDTO.getLatitude());
        landmark.setLongitude(landmarkDTO.getLongitude());
        return landmark;
    }

    public static List<Landmark> toEntity(List<LandmarkDTO> landmarks) {
        return landmarks.stream()
                .map(LandmarkConverter::toEntity)
                .collect(Collectors.toList());
    }
}
