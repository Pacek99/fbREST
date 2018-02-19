/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrik Rojek
 */
public class EventREST {
    
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

    /**
     * The list of ids of events that are resolved as equal to this one (not including this one).
     */
    public List<String> equalEvents = new ArrayList<>();

    /**
     * The id of the event that this event was merged to. Null, if this event hasn't been merged. If it's not null, we
     * should not include this event during the searching.
     */
    public String mergedEventId;

    /**
     * The list of ids of events that've been marked as sub-events of this one. The value is not null.
     */
    public List<String> subEvents = new ArrayList<>();

    /**
     * The id of the parent event of this event. Null, if there's no parent event.
     */
    public String parentEventId;
}
