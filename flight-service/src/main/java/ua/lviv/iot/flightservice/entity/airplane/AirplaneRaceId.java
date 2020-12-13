package ua.lviv.iot.flightservice.entity.airplane;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class AirplaneRaceId implements Serializable {
    private Long airplaneId;
    private Long raceId;
}
