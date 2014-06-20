package com.dmytro.onemoreweatherapp.app.yahoo.api.parsing;

import com.dmytro.onemoreweatherapp.app.model.City;
import com.dmytro.onemoreweatherapp.app.model.Forecast;
import com.dmytro.onemoreweatherapp.app.model.Temperature;
import com.dmytro.onemoreweatherapp.app.model.Wind;
import com.dmytro.onemoreweatherapp.app.yahoo.api.Storage;
import org.xmlpull.v1.XmlPullParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: Dmytro Vynokurov
 * Date: 19.06.14
 * Time: 22:10
 */
public class ForecastParser implements RSSParser {

    private City city;

    public ForecastParser(City city){
        this.city = city;
    }

    @Override
    public void parseAndStrore(XmlPullParser parser) {
        int event;
        String text = null;
        int parameterCount;
        Map<String,String> parameters = null;

        Forecast forecast = new Forecast();

        try {
            event = parser.getEventType();
            int j=1;
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        parameterCount = parser.getAttributeCount();
                        parameters = new HashMap<String, String>(parameterCount);
                        for(int i=0;i<parameterCount;i++){
                            parameters.put(parser.getAttributeName(i),parser.getAttributeValue(i));
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals("title") && text.length()>17 && forecast.getCity()==null){
                            forecast.setCity(city);
                        }else
                        if(name.equals("lastBuildDate")){
                            DateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a zzz");
                            Date date = sdf.parse(text);
                            Calendar calendar = new GregorianCalendar();
                            calendar.setTime(date);
                            forecast.setDate(calendar);
                        }else if(name.equals("yweather:condition")){
                            forecast.setCurrentTemperature(
                                    Temperature.inCelsius(Integer.valueOf(parameters.get("temp"))));
                            forecast.setCurrentDescription(parameters.get("text"));
                        }else if(name.equals("yweather:wind")){
                            forecast.setWind(new Wind(
                                    Integer.valueOf(parameters.get("direction")),
                                    Double.valueOf(parameters.get("speed"))));
                        }else if(name.equals("yweather:atmosphere")){
                            forecast.setHumidity(Integer.valueOf(parameters.get("humidity")));
                            forecast.setPressure(Double.valueOf(parameters.get("pressure")));
                        }
                }
                event = parser.next();
                j++;
            }
            Storage storage = Storage.getInstance();
            Map<City,Forecast> forecasts = storage.getForecasts();
            forecasts.put(forecast.getCity(), forecast);
        } catch (Throwable e) {
            Storage.getInstance().setFetchSuccessful(false);
        }
    }
}
