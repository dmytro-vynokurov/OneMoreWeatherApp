package com.dmytro.onemoreweatherapp.app.logic;

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
}
