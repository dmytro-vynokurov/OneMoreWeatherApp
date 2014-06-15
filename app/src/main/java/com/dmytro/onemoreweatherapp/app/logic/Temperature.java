package com.dmytro.onemoreweatherapp.app.logic;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 4:08
 */
public class Temperature {
    private static final long serialVersionUID = 12341L;

    private double temperatureCelsius;

    private Temperature(double temperatureCelsius){
        this.temperatureCelsius = temperatureCelsius;
    }

    public static Temperature instanceCelsius(double temperature){
        return new Temperature(temperature);
    }

    public static Temperature instanceFahrenheit(double temperature){
        return new Temperature(fahrenheitAsCelsius(temperature));
    }

    private static double celsiusAsFahrenheit(double temperatureCelsius){
        return temperatureCelsius * 9. / 5 + 32;
    }

    private static double fahrenheitAsCelsius(double temperatureFahrenheit){
        return (temperatureFahrenheit-32)  * 5. / 9;
    }

    public double getTemperatureCelsius(){
        return temperatureCelsius;
    }

    public double getTemperatureFahrenheit(){
        return celsiusAsFahrenheit(temperatureCelsius);
    }
}
