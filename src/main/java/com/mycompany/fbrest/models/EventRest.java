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
 * Represents either directly a facebook event, or an event downloaded from other website. This is supposed to be the
 * representation of a JSON file that is being sent via REST api.
 */
public class EventRest
{
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
    public LocalDateTime startTime;

    /**
     * The time when the event ends.
     */
    public LocalDateTime endTime;

    /**
     * The url to the event source of this event (from which this event was downloaded).
     */
    public String eventSourceUrl;

    /**
     * The type of the event.
     */
    public EventType eventType;

    /**
     * The http url to the event.
     */
    public String url;

    /**
     * The list of ids of events that've been marked as sub-events of this one. The value is not null. The interior
     * events are not supposed to have any sub-events (i.e. the level of nesting should be reduced to one).
     */
    public List<EventRest> subEvents = new ArrayList<>();

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getEventSourceUrl() {
        return eventSourceUrl;
    }

    public void setEventSourceUrl(String eventSourceUrl) {
        this.eventSourceUrl = eventSourceUrl;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<EventRest> getSubEvents() {
        return subEvents;
    }

    public void setSubEvents(List<EventRest> subEvents) {
        this.subEvents = subEvents;
    }
    
    
}
