package ua.lviv.iot.flightservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/mocked-controller")
public class MockedLogController {
    private static final String MOCK_LOGGED = "Event has been logged!";

    @GetMapping
    public String mockGet() {
        log.info("Mocked log for GET request!");
        return MOCK_LOGGED;
    }

    @PostMapping
    public String mockPost() {
        log.info("Mocked log for POST request!");
        return MOCK_LOGGED;
    }
}
