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

    public City(String name, long id) {
        this.name = name;
        this.id = id;
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
}
