package ua.lviv.iot.flightservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.flightservice.converter.AirplaneConverter;
import ua.lviv.iot.flightservice.converter.RaceConverter;
import ua.lviv.iot.flightservice.dto.AirplaneDTO;
import ua.lviv.iot.flightservice.dto.RaceDTO;
import ua.lviv.iot.flightservice.entity.race.Race;
import ua.lviv.iot.flightservice.service.AirplaneService;
import ua.lviv.iot.flightservice.service.RaceService;

import java.util.List;

@RestController
@RequestMapping("/airplane")
@RequiredArgsConstructor
public class AirplaneController {
    private final RaceService raceService;
    private final AirplaneService airplaneService;

    @GetMapping("/all")
    public List<AirplaneDTO> getAll() {
        return AirplaneConverter.toDTO(airplaneService.getAll());
    }

    @GetMapping("/detailed")
    public AirplaneDTO get(@RequestParam String airplaneIdentifier) {
        return AirplaneConverter.toDTO(airplaneService.getWithRaces(airplaneIdentifier));
    }

    @GetMapping("/current-race")
    public RaceDTO getCurrent(@RequestParam String airplaneIdentifier) {
        return RaceConverter.toDTO(airplaneService.getCurrent(airplaneIdentifier).orElse(new Race()));
    }

    @PostMapping("/{id}/add-race")
    public RaceDTO add(@PathVariable("id") Long id, @RequestBody RaceDTO raceDTO) {
        Race race = raceService.get(raceDTO);
        return RaceConverter.toDTO(airplaneService.add(id, race));
    }
}
