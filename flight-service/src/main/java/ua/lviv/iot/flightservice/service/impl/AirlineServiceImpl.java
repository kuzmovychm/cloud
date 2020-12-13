package ua.lviv.iot.flightservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.flightservice.entity.Airline;
import ua.lviv.iot.flightservice.exception.CustomException;
import ua.lviv.iot.flightservice.repo.AirlineRepo;
import ua.lviv.iot.flightservice.service.AirlineService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepo airlineRepo;

    @Override
    public Airline save(Airline airline) {
        return airlineRepo.save(airline);
    }

    @Override
    public List<Airline> getAll() {
        return airlineRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        airlineRepo.deleteById(id);
    }

    @Override
    public Airline get(Long id) {
        return airlineRepo.findById(id).orElseThrow(() -> new CustomException("No Airline with id: " + id));
    }
}
