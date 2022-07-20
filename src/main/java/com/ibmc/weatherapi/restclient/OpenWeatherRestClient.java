package com.ibmc.weatherapi.restclient;

import com.ibmc.weatherapi.exception.ApplicationException;
import com.ibmc.weatherapi.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherRestClient {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    //  @Autowired
    RestTemplate restTemplate = new RestTemplate();

    public OpenWeatherResponse getWeather(String city, String state, String country) {
        OpenWeatherResponse openWeatherResponse = null;
        try {
            //TODO: replace with StringBuilder
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "," +
                    state + "," + country + "&units=metric&appid=79dc2d0d64e5cd2d2a41b5ae677b7c44";
            openWeatherResponse = restTemplate.getForObject(url, OpenWeatherResponse.class);
        } catch (HttpClientErrorException e) {
            log.error("Unable to retrieve weather city={},state={},country={}", city, state, country);
            if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
                //handle 404
                throw new NotFoundException("City not found", e);
            }
            throw new ApplicationException("Unable to process request", e);
        }
        return openWeatherResponse;
    }
}
