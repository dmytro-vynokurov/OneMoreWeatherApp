package com.dmytro.onemoreweatherapp.app.model;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 7:25
 */
public class Util {
    public static int millibarToMillimetersHg(double mBars){
        return (int) Math.round(mBars/13.3322387);
    }
}
