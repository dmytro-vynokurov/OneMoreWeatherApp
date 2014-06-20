package com.dmytro.onemoreweatherapp.app.yahoo.api;

import android.content.Context;
import com.dmytro.onemoreweatherapp.app.model.City;
import com.dmytro.onemoreweatherapp.app.model.Forecast;
import com.dmytro.onemoreweatherapp.app.model.Temperature;

import java.io.*;
import java.util.*;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 7:37
 */
public class Storage implements Serializable{
    private static final long serialVersionUID = 123451;
    private static String STORAGE_FILENAME = "storage";

    private static Storage instance = null;

    public static synchronized Storage getInstance(){
        if (instance==null) instance = new Storage();
        return instance;
    }

    private Map<City,Forecast> forecasts;
    private boolean fetchSuccessful = true;
    private Calendar timestamp;
    private City favouriteCity;
    private Temperature.Scale temperatureScale = Temperature.Scale.CELSIUS;


    private Storage() {
        Comparator<City> comparator = new CityViewComparator();
        forecasts = new TreeMap<City, Forecast>(comparator);
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

    public List<City> getCitiesList(){
        return new ArrayList<City>(getCities());
    }

    public int numberOfCities(){
        return forecasts.size();
    }

    public boolean isFetchSuccessful() {
        return fetchSuccessful;
    }

    public void setFetchSuccessful(boolean fetchSuccessful) {
        this.fetchSuccessful = fetchSuccessful;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public City getFavouriteCity() {
        if(favouriteCity!=null)return favouriteCity;
        else return getCitiesList().get(0);
    }

    public void setFavouriteCity(City favouriteCity) {
        this.favouriteCity = favouriteCity;
    }

    public Temperature.Scale getTemperatureScale() {
        return temperatureScale;
    }

    public void setTemperatureScale(Temperature.Scale temperatureScale) {
        this.temperatureScale = temperatureScale;
    }

    public boolean isDataObsolete(){
        int day = 1000*60*60*24;
        Calendar now = Calendar.getInstance();
        long nowMs = now.getTimeInMillis();
        long timestampMs = timestamp.getTimeInMillis();
        return nowMs-timestampMs>day;

    }

    public void persist(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(STORAGE_FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(Context context){
        try {
            FileInputStream fis = context.openFileInput(STORAGE_FILENAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object readObject = is.readObject();
            is.close();

            if(readObject != null && readObject instanceof Storage) {
                instance = (Storage) readObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class CityViewComparator implements Serializable, Comparator<City> {
        @Override
        public int compare(City lhs, City rhs) {
            return lhs.getViewIndex()-rhs.getViewIndex();
        }
    }
}
