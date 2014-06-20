package com.dmytro.onemoreweatherapp.app.yahoo.api.parsing;

import com.dmytro.onemoreweatherapp.app.yahoo.api.Storage;
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
public class RSSFetcher {
    private RSSParser rssParser;

    public RSSFetcher(RSSParser rssParser){
        this.rssParser = rssParser;
    }

    public void fetchAndStoreRSS(final String urlString) {
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
                    XmlPullParser xmlParser = xmlFactoryObject.newPullParser();
                    xmlParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    xmlParser.setInput(stream, null);
                    rssParser.parseAndStrore(xmlParser);
                    stream.close();
                    Storage.getInstance().setFetchSuccessful(true);
                } catch (Exception e) {
                    System.out.println("Data fetching failed");
                    Storage.getInstance().setFetchSuccessful(false);
                }
            }
        });
        thread.start();
    }
}
