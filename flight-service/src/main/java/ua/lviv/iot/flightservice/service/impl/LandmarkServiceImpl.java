package ua.lviv.iot.flightservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.flightservice.entity.Landmark;
import ua.lviv.iot.flightservice.exception.CustomException;
import ua.lviv.iot.flightservice.repo.LandmarkRepo;
import ua.lviv.iot.flightservice.service.LandmarkService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {
    private final LandmarkRepo landmarkRepo;

    @Override
    public Landmark save(Landmark landmark) {
        return landmarkRepo.save(landmark);
    }

    @Override
    public List<Landmark> save(Iterable<Landmark> landmarks) {
        return landmarkRepo.saveAll(landmarks);
    }

    @Override
    public List<Landmark> getAll() {
        return landmarkRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        landmarkRepo.deleteById(id);
    }

    @Override
    public Landmark get(Long id) {
        return landmarkRepo.findById(id).orElseThrow(() -> new CustomException("No landmark with id: " + id));
    }

    @Override
    public List<Landmark> get(List<Long> ids) {
        return landmarkRepo.findAllById(ids);
    }
}
