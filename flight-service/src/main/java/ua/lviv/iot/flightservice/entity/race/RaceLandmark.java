package ua.lviv.iot.flightservice.entity.race;

import lombok.Getter;
import lombok.Setter;
import ua.lviv.iot.flightservice.entity.Landmark;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class RaceLandmark {
    @EmbeddedId
    private RaceLandmarkId id = new RaceLandmarkId();

    @ManyToOne
    @MapsId("raceId")
    private Race race;

    @ManyToOne
    @MapsId("landmarkId")
    private Landmark landmark;

    @Column(name = "ordinal_number", nullable = false)
    private Long ordinalNumber;
}
