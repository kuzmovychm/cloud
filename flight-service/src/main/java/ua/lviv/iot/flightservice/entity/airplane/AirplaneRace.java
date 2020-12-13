package ua.lviv.iot.flightservice.entity.airplane;

import lombok.Getter;
import lombok.Setter;
import ua.lviv.iot.flightservice.entity.race.Race;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Getter
@Setter
@Entity
public class AirplaneRace {
    @EmbeddedId
    private AirplaneRaceId id = new AirplaneRaceId();

    @ManyToOne
    @MapsId("airplaneId")
    private Airplane airplane;

    @ManyToOne
    @MapsId("raceId")
    private Race race;

    private Boolean isActive;
}
