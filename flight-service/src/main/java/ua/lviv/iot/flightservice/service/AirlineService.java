package ua.lviv.iot.flightservice.service;

import ua.lviv.iot.flightservice.entity.Airline;

import java.util.List;

public interface AirlineService {
    Airline save(Airline airline);

    List<Airline> getAll();

    void delete(Long id);

    Airline get(Long id);
}
