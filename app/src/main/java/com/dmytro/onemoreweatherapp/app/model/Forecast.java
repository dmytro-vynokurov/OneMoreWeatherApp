package com.dmytro.onemoreweatherapp.app.model;

import com.google.android.gms.common.images.WebImage;

import java.util.Calendar;

/**
 * Keeps data about forecast for current day
 */
public class Forecast {
    private Calendar date;
    private City city;
    private Temperature currentTemperature;
    private WebImage currentImage;
    private String currentDescription;
    private Wind wind;
    private int pressure;
    private int humidity;
    private Calendar timestamp;

    public Forecast() {
        timestamp = Calendar.getInstance();
    }

    public Forecast(Calendar date, City city, Temperature currentTemperature,
                    WebImage currentImage, String currentDescription,
                    Wind wind, int pressure, int humidity) {
        this();
        this.date = date;
        this.city = city;
        this.currentTemperature = currentTemperature;
        this.currentImage = currentImage;
        this.currentDescription = currentDescription;
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

    public void setPressure(double pressureInMillibars){
        this.pressure = millibarToMillimetersHg(pressureInMillibars);
    }

    private static int millibarToMillimetersHg(double mBars){
        return (int) Math.round(mBars/13.3322387)*10;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }
}
