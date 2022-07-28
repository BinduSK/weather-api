package com.ibmc.weatherapi.restclient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {
    Double temp;
    @JsonProperty("feels_like")
    Double feelslike;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(Double feelslike) {
        this.feelslike = feelslike;
    }
}
