/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

/**
 * Represents a geographical location.
 */
public class Location
{
    /**
     * The city (such as Košice).
     */
    public String city;

    /**
     * The country (such as Slovakia).
     */
    public String country;

    /**
     * The latitude.
     */
    public double latitude;

    /**
     * The longitude.
     */
    public double longitude;

    /**
     * The street (such as Jesenna 5)
     */
    public String street;

    /**
     * The zip code (such as 040 01)
     */
    public String zip;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    
}
