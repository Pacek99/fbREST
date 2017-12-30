/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

/**
 * Represents a place (where an event occurs).
 */
public class Place
{
    /**
     * The name of the place (such as Wembley Stadium).
     */
    public String name;

    /**
     * The location of the place.
     */
    public Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    
}
