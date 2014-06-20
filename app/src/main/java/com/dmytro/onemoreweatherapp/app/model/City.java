package com.dmytro.onemoreweatherapp.app.model;

import java.io.Serializable;

/**
 * User: Dmytro Vynokurov
 * Date: 15.06.14
 * Time: 4:17
 */
public class City implements Serializable{
    private static final long serialVersionUID = 1231L;

    private String name;
    private long id;
    private int viewIndex;

    public City(long id,String name) {
        this.name = name;
        this.id = id;
    }

    public City(long id, String name, int viewIndex){
        this(id,name);
        this.viewIndex = viewIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getViewIndex() {
        return viewIndex;
    }

    public void setViewIndex(int viewIndex) {
        this.viewIndex = viewIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ! (o instanceof City)) return false;

        City city = (City) o;

        return id == city.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return name;
    }
}
