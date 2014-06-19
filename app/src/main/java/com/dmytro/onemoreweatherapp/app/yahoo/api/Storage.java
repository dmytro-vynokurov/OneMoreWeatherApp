package com.dmytro.onemoreweatherapp.app.yahoo.api;

import com.dmytro.onemoreweatherapp.app.model.Forecast;

import java.io.Serializable;
import java.util.List;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 7:37
 */
public class Storage implements Serializable{
    private static final long serialVersionUID = 123451;

    private List<Forecast> forecasts;

    private static Storage instance = null;

    public static synchronized Storage getInstance(){
        if (instance==null) instance = new Storage();
        return instance;
    }

    private Storage() {}

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
