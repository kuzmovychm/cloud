package ua.lviv.iot.flightservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.lviv.iot.flightservice.entity.race.Race;

import java.util.List;
import java.util.Optional;

public interface RaceRepo extends JpaRepository<Race, Long> {
    @Query("select r from Race r join fetch r.landmarks where r.identifier =:identifier")
    Optional<Race> getWithLandmarks(@Param("identifier") String identifier);

    @Query("select r from Race r join fetch r.landmarks")
    List<Race> getWithLandmarks();
}
