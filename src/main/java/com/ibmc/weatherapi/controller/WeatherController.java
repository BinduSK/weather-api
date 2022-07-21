package com.ibmc.weatherapi.controller;

import com.ibmc.weatherapi.dto.WeatherResponse;
import com.ibmc.weatherapi.exception.ApplicationException;
import com.ibmc.weatherapi.exception.NotFoundException;
import com.ibmc.weatherapi.restclient.OpenWeatherResponse;
import com.ibmc.weatherapi.restclient.OpenWeatherRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class WeatherController {
    @Autowired
    OpenWeatherRestClient openWeatherRestClient;

    @GetMapping("/weather")
    public WeatherResponse getWeather(@RequestParam(required = true) String city,
                                      @RequestParam(required = false) String state,
                                      @RequestParam(required = false) String country) {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCity(city);
        weatherResponse.setState(state);
        weatherResponse.setCountry(country);
        try {
            OpenWeatherResponse openWeatherResponse = openWeatherRestClient.getWeather(city, state, country);
            weatherResponse.setCurrentTemperature(openWeatherResponse.getMain().getTemp());
        } catch (NotFoundException nfe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
        } catch (ApplicationException ae) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Process failed");
        }
        return weatherResponse;
    }
}
