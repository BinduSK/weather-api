package com.ibmc.weatherapi.service;

import com.ibmc.weatherapi.domain.Weather;
import com.ibmc.weatherapi.restclient.OpenWeatherResponse;
import com.ibmc.weatherapi.restclient.OpenWeatherRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    @Autowired
    OpenWeatherRestClient openWeatherRestClient;

    public Weather getWeather(String city, String state, String country) {
        Weather weather = new Weather();
        weather.setCity(city);
        weather.setState(state);
        weather.setCountry(country);
        OpenWeatherResponse openWeatherResponse = openWeatherRestClient.getWeather(city, state, country);
        weather.setCurrentTemperature(openWeatherResponse.getMain().getTemp());
        weather.setFeelslike(openWeatherResponse.getMain().getFeelslike());
        return weather;
    }
}
