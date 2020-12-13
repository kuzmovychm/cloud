package ua.lviv.iot.flightservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.flightservice.converter.AirlineConverter;
import ua.lviv.iot.flightservice.dto.AirlineDTO;
import ua.lviv.iot.flightservice.entity.Airline;
import ua.lviv.iot.flightservice.service.AirlineService;

import java.util.List;

@RestController
@RequestMapping("/airline")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

    @GetMapping("/{id}")
    public AirlineDTO get(@PathVariable("id") Long id) {
        return AirlineConverter.toDTO(airlineService.get(id));
    }

    @GetMapping("/all")
    public List<AirlineDTO> getAll() {
        return AirlineConverter.toDTO(airlineService.getAll());
    }

    @PostMapping
    public AirlineDTO create(@RequestBody AirlineDTO airlineDTO) {
        return AirlineConverter.toDTO(airlineService.save(AirlineConverter.toEntity(airlineDTO)));
    }

    @PutMapping
    public AirlineDTO update(@RequestBody AirlineDTO airlineDTO) {
        return AirlineConverter.toDTO(airlineService.save(AirlineConverter.toEntity(airlineDTO)));
    }

}
