package com.goeuro.client.rest.domain;

import java.util.List;

/**
 * Created by wamalalawrence on 15/11/04.
 */
public class LocationDataWrapper {
    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
