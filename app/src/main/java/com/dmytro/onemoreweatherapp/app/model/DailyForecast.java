package com.dmytro.onemoreweatherapp.app.model;

import com.google.android.gms.common.images.WebImage;

import java.util.Calendar;
import java.util.List;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 4:59
 */
public class DailyForecast {
    private Calendar date;
    private City city;
    private Temperature currentTemperature;
    private WebImage currentImage;
    private String currentDescription;
    private List<DailyForecast> dailyForecasts;
    private Wind wind;
    private int pressure;
    private int humidity;

    public DailyForecast(Calendar date, City city, Temperature currentTemperature,
                         WebImage currentImage, String currentDescription, List<DailyForecast> dailyForecasts,
                         Wind wind, int pressure, int humidity) {
        this.date = date;
        this.city = city;
        this.currentTemperature = currentTemperature;
        this.currentImage = currentImage;
        this.currentDescription = currentDescription;
        this.dailyForecasts = dailyForecasts;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Temperature getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Temperature currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public WebImage getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(WebImage currentImage) {
        this.currentImage = currentImage;
    }

    public String getCurrentDescription() {
        return currentDescription;
    }

    public void setCurrentDescription(String currentDescription) {
        this.currentDescription = currentDescription;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
