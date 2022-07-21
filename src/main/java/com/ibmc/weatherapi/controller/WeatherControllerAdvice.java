package com.ibmc.weatherapi.controller;

import com.ibmc.weatherapi.exception.ApplicationException;
import com.ibmc.weatherapi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class WeatherControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest req) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 404);
        response.put("message", "city not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException e, WebRequest req) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 500);
        response.put("message", "unable to process request, please try again");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
