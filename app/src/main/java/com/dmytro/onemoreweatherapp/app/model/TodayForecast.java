package com.dmytro.onemoreweatherapp.app.model;

import com.google.android.gms.common.images.WebImage;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 5:02
 */
public class TodayForecast {
    private DayTime dayTime;
    private WebImage image;
    private Temperature weatherForecast;

    public TodayForecast(DayTime dayTime, WebImage image, Temperature weatherForecast) {
        this.dayTime = dayTime;
        this.image = image;
        this.weatherForecast = weatherForecast;
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
