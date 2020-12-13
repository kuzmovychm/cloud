package ua.lviv.iot.flightservice.service;

import ua.lviv.iot.flightservice.entity.Landmark;

import java.util.List;

public interface LandmarkService {
    Landmark save(Landmark landmark);

    List<Landmark> save(Iterable<Landmark> landmarks);

    List<Landmark> getAll();

    void delete(Long id);

    Landmark get(Long id);

    List<Landmark> get(List<Long> ids);
}
