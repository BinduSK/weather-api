package com.ibmc.weatherapi.controller;

import com.ibmc.weatherapi.domain.Weather;
import com.ibmc.weatherapi.restclient.OpenWeatherResponse;
import com.ibmc.weatherapi.restclient.OpenWeatherRestClient;
import com.ibmc.weatherapi.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather")
    public Weather getWeather(@RequestParam(required = true) String city,
                              @RequestParam(required = false) String state,
                              @RequestParam(required = false) String country) {
        Weather weather = weatherService.getWeather(city, state, country);
        return weather;
    }
}
