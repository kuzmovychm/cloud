package ua.lviv.iot.flightservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {
    @GetMapping
    public String defaultPath() {
        return "I am healthy default path!";
    }

    @GetMapping("/custom")
    public String customPath() {
        return "I am healthy custom path!";
    }
}
