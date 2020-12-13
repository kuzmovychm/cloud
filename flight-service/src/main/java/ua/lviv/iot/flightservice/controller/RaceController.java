package ua.lviv.iot.flightservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.flightservice.converter.RaceConverter;
import ua.lviv.iot.flightservice.dto.RaceDTO;
import ua.lviv.iot.flightservice.service.RaceService;

import java.util.List;

@RestController
@RequestMapping("/race")
@RequiredArgsConstructor
public class RaceController {
    private final RaceService raceService;

    @GetMapping("/all")
    private List<RaceDTO> getAll() {
        return RaceConverter.toDTO(raceService.getAll());
    }

    @GetMapping
    public RaceDTO get(@RequestParam String identifier) {
        return RaceConverter.toDTO(raceService.get(identifier));
    }

    @PostMapping
    public RaceDTO create(@RequestBody RaceDTO raceDTO) {
        return RaceConverter.toDTO(raceService.create(raceDTO));
    }

    @PutMapping
    public RaceDTO update(@RequestBody RaceDTO raceDTO) {
        return RaceConverter.toDTO(raceService.update(raceDTO));
    }
}
