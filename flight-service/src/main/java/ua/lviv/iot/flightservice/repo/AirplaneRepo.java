package ua.lviv.iot.flightservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.lviv.iot.flightservice.entity.airplane.Airplane;

import java.util.Optional;

public interface AirplaneRepo extends JpaRepository<Airplane, Long> {

    @Query("select a from Airplane a join fetch a.races where a.identifier =:identifier")
    Optional<Airplane> getWithRaces(@Param("identifier") String identifier);

}
