/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

/**
 *
 * @author Patrik Rojek
 */
public class LocationREST {

    /**
     * The city (such as Košice).
     */
    public String city;

    /**
     * The country (such as Slovakia).
     */
    public String country;

    /**
     * The geographical coordinates
     */
    public double latitude;

    public double longitude;

    /**
     * The street (such as Jesenna 5)
     */
    public String street;

    /**
     * The zip code (such as 040 01)
     */
    public String zip;
}
