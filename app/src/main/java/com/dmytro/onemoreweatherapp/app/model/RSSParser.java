package com.dmytro.onemoreweatherapp.app.model;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 4:34
 */
public class RSSParser {
    public enum RSSType{FORECAST,CITIES}

    public static void fetchAndStoreRSS(final String urlString, final RSSType rssType) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    XmlPullParserFactory xmlFactoryObject;
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = xmlFactoryObject.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(stream, null);
                    switch (rssType){
                        case FORECAST:
                            parseForecastRSSAndStoreIt(parser);
                            break;
                        case CITIES:
                            parseCitiesRSSAndStoreIt(parser);
                            break;
                        default:
                            throw new IllegalArgumentException("Only forecast and cities RSS supported");
                    }
                    stream.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    private static void parseForecastRSSAndStoreIt(XmlPullParser parser) {
        int event;
        String text = null;
        int parameterCount;
        Map<String,String> parameters = null;

        DailyForecast dailyForecast = new DailyForecast();

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
                        System.out.println("------NAME"+j+":"+name);
                        System.out.println("------TEXT"+j+":"+text);
                        System.out.println("------PARS"+j+":" + parameters);
                        if (name.equals("title") && text.length()>17){
                            String cityName = text.substring(17,text.length());
                            System.out.println("------CITY"+j+":" + cityName);
                            //TODO: use CityStorage
                            dailyForecast.setCity(new City(cityName, 1));
                        }else if(name.equals("lastBuildDate")){
                            DateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a zzz");
                            Date date = sdf.parse(text);
                            Calendar calendar = new GregorianCalendar();
                            calendar.setTime(date);
                            System.out.println("------TIME"+j+":" + calendar);
                            dailyForecast.setDate(calendar);
                        }else if(name.equals("yweather:condition")){
                            dailyForecast.setCurrentTemperature(
                                    Temperature.instanceCelsius(Integer.valueOf(parameters.get("temp"))));
                            dailyForecast.setCurrentDescription(parameters.get("text"));
                            System.out.println("------TEMP"+j+":" + dailyForecast.getCurrentTemperature());
                            System.out.println("------DESC"+j+":" + dailyForecast.getCurrentDescription());
                        }else if(name.equals("yweather:wind")){
                            dailyForecast.setWind(new Wind(
                                    Integer.valueOf(parameters.get("direction")),
                                    Double.valueOf(parameters.get("speed"))));
                            System.out.println("------WIND"+j+":" + dailyForecast.getWind());
                        }else if(name.equals("yweather:atmosphere")){
                            dailyForecast.setHumidity(Integer.valueOf(parameters.get("humidity")));
                            dailyForecast.setPressure(
                                    Util.millibarToMillimetersHg(Double.valueOf(parameters.get("pressure"))));
                            System.out.println("------HUMI"+j+":" + dailyForecast.getHumidity());
                            System.out.println("------PRES"+j+":" + dailyForecast.getPressure());
                        }
                }
                event = parser.next();
                j++;
            }
            ForecastStorage storage = ForecastStorage.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //TODO: implement parser for cities list
    private static void parseCitiesRSSAndStoreIt(XmlPullParser parser){

    }
}
