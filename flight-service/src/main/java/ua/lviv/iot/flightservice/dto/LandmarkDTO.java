package ua.lviv.iot.flightservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public class LandmarkDTO {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Long ordinalNumber;
}
