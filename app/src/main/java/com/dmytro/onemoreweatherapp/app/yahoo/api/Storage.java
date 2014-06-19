package com.dmytro.onemoreweatherapp.app.yahoo.api;

import com.dmytro.onemoreweatherapp.app.model.City;
import com.dmytro.onemoreweatherapp.app.model.Forecast;

import java.io.Serializable;
import java.util.*;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 7:37
 */
public class Storage implements Serializable{
    private static final long serialVersionUID = 123451;

    private static Storage instance = null;

    public static synchronized Storage getInstance(){
        if (instance==null) instance = new Storage();
        return instance;
    }

    private Map<City,Forecast> forecasts;

    private Storage() {
        forecasts = new HashMap<City, Forecast>();
    }

    public Map<City,Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(Map<City,Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public List<Forecast> getForecastValues(){
        return new ArrayList<Forecast>(forecasts.values());
    }

    public Set<City> getCities(){
        return forecasts.keySet();
    }
}
