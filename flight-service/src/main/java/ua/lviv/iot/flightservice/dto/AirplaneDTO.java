package ua.lviv.iot.flightservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public class AirplaneDTO {
    private Long id;
    private String name;
    private String identifier;
    private Double speed;
    private Long kilometersPast;
    private RaceDTO currentRace;
    private List<RaceDTO> previousRaces;
    private LandmarkDTO currentLocation;
}
