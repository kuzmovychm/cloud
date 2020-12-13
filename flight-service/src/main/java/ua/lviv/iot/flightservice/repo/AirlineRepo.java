package ua.lviv.iot.flightservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.flightservice.entity.Airline;

public interface AirlineRepo extends JpaRepository<Airline, Long> {
}
