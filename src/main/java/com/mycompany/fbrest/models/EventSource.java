/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

/**
 * Represents the source of an event.
 */
public class EventSource
{
    /**
     * The source. In case of facebook api, it should be the parameter directly
     * used in the Facebook (Graph) API.
     */
    public String source;

    /**
     * The type of the source.
     */
    public EventSourceType sourceType;

    /**
     * The default type of events gotten from this source.
     */
    public EventType defaultType;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public EventSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(EventSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public EventType getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(EventType defaultType) {
        this.defaultType = defaultType;
    }
    
    
}
