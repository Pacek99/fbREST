/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

import java.time.LocalDateTime;

/**
 *
 * @author Patrik Rojek
 */
public class MergedEvent {
    
    /**
     * The id of the event. It shouldn't be null. For Facebook events, this should be fb-{id}, where 'id' is the unique
     * id provided by facebook. Using this we can find out if a downloaded event is already stored in the database. The
     * value shouldn't be null.
     */
    public String id;

    /**
     * The place where this event happens.
     */
    public PlaceREST place;

    /**
     * The description of the event.
     */
    public String description;

    /**
     * The time when the event begins.
     */
    public LocalDateTime startTime;

    /**
     * The time when the event ends.
     */
    public LocalDateTime endTime;

    /**
     * The type of the event.
     */
    public String eventType;

    /**
     * The http facebook url to the event.
     */
    public String url;

    /**
     * The http event source url.
     */
    public String eventSourceUrl;
}
