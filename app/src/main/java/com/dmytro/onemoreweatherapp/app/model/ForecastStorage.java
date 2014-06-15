package com.dmytro.onemoreweatherapp.app.model;

import java.io.Serializable;
import java.util.List;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 7:37
 */
public class ForecastStorage implements Serializable{
    private static final long serialVersionUID = 123451;

    private TodayForecast todayForecast;
    private List<DailyForecast> dailyForecasts;

    public ForecastStorage() {}

    public TodayForecast getTodayForecast() {
        return todayForecast;
    }

    public void setTodayForecast(TodayForecast todayForecast) {
        this.todayForecast = todayForecast;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }
}
