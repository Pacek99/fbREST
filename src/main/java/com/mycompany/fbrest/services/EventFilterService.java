/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import events.Launcher;
import events.dataAccess.EventSearchCriteria;
import events.entities.Event;
import java.time.LocalDateTime;

/**
 *
 * @author Patrik Rojek
 */
public class EventFilterService {
    
    public Iterable<Event> filter(Double latitude, Double longitude, Double radius, String startDate){
        EventSearchCriteria esc = new EventSearchCriteria();
        esc.latitude = latitude;
        esc.longitude = longitude;
        esc.radius = radius;
        esc.startDate = LocalDateTime.parse(startDate);
        
        return Launcher.eventService.findAll(esc);
    }
}
