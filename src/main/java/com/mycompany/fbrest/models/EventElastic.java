package com.mycompany.fbrest.models;

import events.entities.Place;

import java.time.LocalDateTime;

/**
 * Represents an event designed according to the facebook graph API event, holding additional information about parent
 * and children and equal events.
 */
public class EventElastic
{
    /**
     * The id of the event. It shouldn't be null. For Facebook events, this should be fb-{id}, where 'id' is the unique
     * id provided by facebook. Using this we can find out if a downloaded event is already stored in the database. The
     * value shouldn't be null.
     */
    
    public String id;

    /**
     * The place where this event happens.
     */
    public Place place;

    /**
     * The description of the event.
     */
    public String description;

    /**
     * The time when the event begins.
     */
    public String start_time;

    /**
     * The time when the event ends.
     */
    public String end_time;

    public String name;

    @Override
    public String toString() {
        return id + place.toString() + description + start_time + end_time + name; //To change body of generated methods, choose Tools | Templates.
    }
}