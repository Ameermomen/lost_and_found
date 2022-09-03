package com.ameermomen.lostandfind.Utils;

public class ItemLocation {

    private String locationName;
    private double longitude;
    private double latitude;

    public ItemLocation(){}

    public String getLocationName() {
        return locationName;
    }

    public ItemLocation setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public ItemLocation setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public ItemLocation setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }
}
