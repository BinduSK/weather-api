package com.ibmc.weatherapi.restclient;


//this structure must match with Json structure of the OpenWeather API response (insomnia)
public class OpenWeatherResponse {
    //main object hold the current temperature from API
    Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
