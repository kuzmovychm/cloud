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
public class RaceDTO {
    private AirlineDTO airline;
    private String identifier;
    private List<LandmarkDTO> landmarks;
    private List<Long> orderedIDs;
}
