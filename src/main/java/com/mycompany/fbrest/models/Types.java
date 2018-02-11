/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

import eventagent.persistence.entities.EventDefaultType;
import eventagent.persistence.entities.SourceType;
import java.util.stream.Stream;

/**
 *
 * @author Patrik Rojek
 */
public class Types {
    
    public String[] sourceTypes = Stream.of(SourceType.values()).map(SourceType::name).toArray(String[]::new);
    
    public String[] eventDefaultTypes = Stream.of(EventDefaultType.values()).map(EventDefaultType::name).toArray(String[]::new);   

    public String[] getSourceTypes() {
        return sourceTypes;
    }

    public void setSourceTypes(String[] sourceTypes) {
        this.sourceTypes = sourceTypes;
    }

    public String[] getEventDefaultTypes() {
        return eventDefaultTypes;
    }

    public void setEventDefaultTypes(String[] eventDefaultTypes) {
        this.eventDefaultTypes = eventDefaultTypes;
    }

    
}
