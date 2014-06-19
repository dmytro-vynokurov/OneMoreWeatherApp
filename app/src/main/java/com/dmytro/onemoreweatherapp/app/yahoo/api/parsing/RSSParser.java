package com.dmytro.onemoreweatherapp.app.yahoo.api.parsing;

import org.xmlpull.v1.XmlPullParser;

/**
 * User: Dmytro Vynokurov
 * Date: 19.06.14
 * Time: 22:07
 */
public interface RSSParser {
    void parseAndStrore(XmlPullParser parser);
}
