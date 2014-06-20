package com.dmytro.onemoreweatherapp.app.yahoo.api;

import android.content.Context;
import com.dmytro.onemoreweatherapp.app.model.City;
import com.dmytro.onemoreweatherapp.app.yahoo.api.parsing.ForecastParser;
import com.dmytro.onemoreweatherapp.app.yahoo.api.parsing.RSSFetcher;

/**
 * User: Dmytro Vynokurov
 * Date: 19.06.14
 * Time: 22:02
 */
public class YahooApi {
    private static String YAHOO_APP_ID = "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA";

    private static String getForecastLink(long cityWoeid){
        return "http://weather.yahooapis.com/forecastrss?w="+cityWoeid+"&u=c";
    }

    private static void updateForecastForCity(City city){
        String apiLink = getForecastLink(city.getId());
        RSSFetcher fetcher = new RSSFetcher(new ForecastParser(city));
        fetcher.fetchAndStoreRSS(apiLink);
    }

    public static void updateAllForecasts(Context context){
        Storage storage = Storage.getInstance();
        for(City city: storage.getCities()){
            updateForecastForCity(city);
            if(!storage.isFetchSuccessful())break;
        }
        if(storage.isFetchSuccessful()) storage.persist(context);
        else storage.load(context);
    }


}
