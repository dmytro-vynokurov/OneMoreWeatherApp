package com.dmytro.onemoreweatherapp.app.model;

import com.google.android.gms.common.images.WebImage;

/**
 * Contains the forecast for part of current day
 */
public class TodayForecast {
    private City city;
    private DayTime dayTime;
    private WebImage image;
    private Temperature weatherForecast;

    public TodayForecast() {
    }

    public TodayForecast(City city, DayTime dayTime, WebImage image, Temperature weatherForecast) {
        this.city = city;
        this.dayTime = dayTime;
        this.image = image;
        this.weatherForecast = weatherForecast;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public DayTime getDayTime() {
        return dayTime;
    }

    public void setDayTime(DayTime dayTime) {
        this.dayTime = dayTime;
    }

    public WebImage getImage() {
        return image;
    }

    public void setImage(WebImage image) {
        this.image = image;
    }

    public Temperature getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(Temperature weatherForecast) {
        this.weatherForecast = weatherForecast;
    }
}
