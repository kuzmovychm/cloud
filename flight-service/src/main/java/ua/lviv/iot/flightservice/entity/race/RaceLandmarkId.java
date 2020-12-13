package ua.lviv.iot.flightservice.entity.race;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RaceLandmarkId implements Serializable {
    private Long raceId;
    private Long landmarkId;
}
