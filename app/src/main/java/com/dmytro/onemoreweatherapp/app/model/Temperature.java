package com.dmytro.onemoreweatherapp.app.model;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 4:08
 */
public class Temperature {
    public enum Scale{CELSIUS, FAHRENHEIT}
    private static final long serialVersionUID = 12341L;

    private Scale currentScale;
    private double temperatureCelsius;

    private Temperature(double temperatureCelsius){
        this.temperatureCelsius = temperatureCelsius;
    }

    public static Temperature instanceCelsius(double temperature){
        Temperature result = new Temperature(temperature);
        result.setCurrentScale(Scale.CELSIUS);
        return result;
    }

    public static Temperature instanceFahrenheit(double temperature){
        Temperature result = new Temperature(fahrenheitAsCelsius(temperature));
        result.setCurrentScale(Scale.FAHRENHEIT);
        return result;
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

    public double getTemperature(Scale scale){
        if(scale==Scale.CELSIUS) return getTemperatureCelsius();
        else return getTemperatureFahrenheit();
    }

    private void setCurrentScale(Scale scale){
        this.currentScale = scale;
    }

    @Override
    public String toString() {
        String scale;
        if(currentScale == Scale.CELSIUS) scale = " C";
        else scale = " F";
        return getTemperature(currentScale)+scale;
    }
}
