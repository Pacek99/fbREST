/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import events.Launcher;
import events.dataAccess.EventSearchCriteria;
import events.entities.Event;
import java.util.List;
import java.time.LocalDateTime;
import jersey.repackaged.com.google.common.collect.Lists;

/**
 *
 * @author Patrik Rojek
 */
public class EventFilterService {
    
    public List<Event> filter(Double latitude, Double longitude, Double radius, String startDate){
        EventSearchCriteria esc = new EventSearchCriteria();
        esc.latitude = latitude;
        esc.longitude = longitude;
        esc.radius = radius;
        esc.startDate = LocalDateTime.parse(startDate);
        
        return Lists.newArrayList(Launcher.eventService.findAll(esc)) ;
    }
}
