package ua.lviv.iot.flightservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.flightservice.entity.Landmark;

import java.util.List;

public interface LandmarkRepo extends JpaRepository<Landmark, Long> {
}
