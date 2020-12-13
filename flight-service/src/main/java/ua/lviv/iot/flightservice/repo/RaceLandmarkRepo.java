package ua.lviv.iot.flightservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.flightservice.entity.race.RaceLandmark;

public interface RaceLandmarkRepo extends JpaRepository<RaceLandmark, Long> {
}
