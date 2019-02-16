package com.graphqljava.poalim.poalimdemo.entities;

public class Atm {
    private String id;
    private String locationName;

    public Atm(String id, String locationName) {
        this.id = id;
        this.locationName = locationName;
    }

    public String getId() {
        return id;
    }

    public String getLocationName() {
        return locationName;
    }
}
