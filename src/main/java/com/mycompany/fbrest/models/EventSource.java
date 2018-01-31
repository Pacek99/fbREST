/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

import eventagent.persistence.entities.EventDefaultType;
import eventagent.persistence.entities.SourceType;

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
    public SourceType sourceType;

    /**
     * The default type of events gotten from this source.
     */
    public EventDefaultType defaultType;
    
    public Integer frequency;

    
    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public EventDefaultType getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(EventDefaultType defaultType) {
        this.defaultType = defaultType;
    }
    
    
}
