package ua.lviv.iot.flightservice.entity.airplane;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;
import ua.lviv.iot.flightservice.entity.Landmark;
import ua.lviv.iot.flightservice.entity.race.Race;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(indexes = @Index(name = "identifier", columnList = "identifier"))
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String identifier;

    private String name;
    private Double speed;
    private Long kilometersPast;

    @ManyToOne
    private Landmark currentLocation;

    @OneToMany(mappedBy = "airplane")
    private List<AirplaneRace> races;

    public void add(AirplaneRace race) {
        if (Objects.isNull(races)) {
            races = new ArrayList<>();
        }

        races.add(race);
    }

}
