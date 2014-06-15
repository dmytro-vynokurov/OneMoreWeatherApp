package com.dmytro.onemoreweatherapp.app.model;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 7:25
 */
public class Util {
    public static int millibarToMillimetersHg(double mBars){
        return (int) Math.round(mBars/13.3322387)*10;
    }

    public static String windDegreesToDirection(int degrees){
        if(degrees>=12 && degrees<34) return "NNE";
        if(degrees>=34 && degrees<56) return "NE";
        if(degrees>=56 && degrees<79) return "ENE";
        if(degrees>=79 && degrees<101) return "E";
        if(degrees>=101 && degrees<124) return "ESE";
        if(degrees>=124 && degrees<146) return "SE";
        if(degrees>=146 && degrees<167) return "SSE";
        if(degrees>=167 && degrees<191) return "S";
        if(degrees>=191 && degrees<214) return "SSW";
        if(degrees>=214 && degrees<236) return "SW";
        if(degrees>=236 && degrees<259) return "WSW";
        if(degrees>=259 && degrees<281) return "W";
        if(degrees>=281 && degrees<304) return "WNW";
        if(degrees>=304 && degrees<326) return "NW";
        if(degrees>=326 && degrees<349) return "NNW";
        return "N";
    }
}
