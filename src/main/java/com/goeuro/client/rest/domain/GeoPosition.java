package com.goeuro.client.rest.domain;

import java.io.Serializable;

/**
 * Created by wamalalawrence on 15/11/04.
 * GeoPosition model object
 */

public class GeoPosition implements Serializable{

    private double latitude;
    private double longitude;

    //getter and setter methods
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

}
