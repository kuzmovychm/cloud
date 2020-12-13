package ua.lviv.iot.flightservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.flightservice.converter.LandmarkConverter;
import ua.lviv.iot.flightservice.dto.LandmarkDTO;
import ua.lviv.iot.flightservice.service.LandmarkService;

import java.util.List;

@RestController
@RequestMapping("/landmark")
@RequiredArgsConstructor
public class LandmarkController {
    private final LandmarkService landmarkService;

    @GetMapping("/all")
    public List<LandmarkDTO> getAll() {
        return LandmarkConverter.toDTO(landmarkService.getAll());
    }

    @GetMapping("/{id}")
    public LandmarkDTO get(@PathVariable("id") Long id) {
        return LandmarkConverter.toDTO(landmarkService.get(id));
    }

    @PostMapping
    public LandmarkDTO create(@RequestBody LandmarkDTO landmarkDTO) {
        return LandmarkConverter.toDTO(landmarkService.save(LandmarkConverter.toEntity(landmarkDTO)));
    }

    @PutMapping
    public LandmarkDTO update(@RequestBody LandmarkDTO landmarkDTO) {
        return LandmarkConverter.toDTO(landmarkService.save(LandmarkConverter.toEntity(landmarkDTO)));
    }
}
