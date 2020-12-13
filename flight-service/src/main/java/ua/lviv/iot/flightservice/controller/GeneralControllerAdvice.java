package ua.lviv.iot.flightservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.lviv.iot.flightservice.exception.CustomException;

@ControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleDefault(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
