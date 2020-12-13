package ua.lviv.iot.flightservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.lviv.iot.flightservice.entity.airplane.AirplaneRace;
import ua.lviv.iot.flightservice.entity.race.Race;

import java.util.Optional;

public interface AirplaneRaceRepo extends JpaRepository<AirplaneRace, Long> {

    @Query("select ar.race from AirplaneRace ar join fetch ar.race.landmarks where ar.airplane.identifier =:airplaneIdentifier and ar.isActive = true")
    Optional<Race> getCurrent(@Param("airplaneIdentifier") String airplaneIdentifier);

    @Modifying
    @Query("update AirplaneRace ar set ar.isActive = false where ar.airplane.id =:airplaneID")
    void setActiveFalse(@Param("airplaneID") Long airplaneID);

    Boolean existsByAirplaneIdAndRaceIdentifier(Long airplaneID, String raceIdentifier);
}
