package com.dmytro.onemoreweatherapp.app.model;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 5:07
 */
public class Wind {
    private String direction;
    private double speed;

    public Wind(String direction, double speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public Wind(int direction,double speed){
        this(Util.windDegreesToDirection(direction),speed);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Wind: " + direction + ", " +speed+"km/h";
    }
}
