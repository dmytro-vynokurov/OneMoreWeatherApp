package com.dmytro.onemoreweatherapp.app.model;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 4:34
 */
public class RSSParser {
    private String urlString;

    public RSSParser(String url) {
        this.urlString = url;
    }
    private void parseRSSAndStoreIt(XmlPullParser parser) {
        int event;
        String text = null;
        try {
            event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals("title")) {
                            title = text;
                        } else if (name.equals("link")) {
                            link = text;
                        } else if (name.equals("description")) {
                            description = text;
                        }
                        break;
                }
                event = parser.next();
            }
            ForecastStorage storage = ForecastStorage.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void fetchAndStoreRSS() {
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
                    parseRSSAndStoreIt(parser);
                    stream.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
}
