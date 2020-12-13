package ua.lviv.iot.flightservice.entity.race;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;
import ua.lviv.iot.flightservice.entity.Airline;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(indexes = @Index(name = "identifier", columnList = "identifier"))
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @ManyToOne
    private Airline airline;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "race")
    private List<RaceLandmark> landmarks;

    public void add(RaceLandmark raceLandmark) {
        if (CollectionUtils.isEmpty(landmarks)) {
            landmarks = new ArrayList<>();
        }

        landmarks.add(raceLandmark);
    }

    public void remove(RaceLandmark raceLandmark) {
        if (CollectionUtils.isEmpty(landmarks)) {
            landmarks = new ArrayList<>();
        }

        landmarks.remove(raceLandmark);
    }
}
