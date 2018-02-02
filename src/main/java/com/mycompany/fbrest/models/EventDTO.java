/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

import eventagent.persistence.entities.EventDefaultType;
import java.util.List;
import events.entities.Event;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Patrik Rojek
 */
public class EventDTO {
    
    public static List<Event> transform(List<EventElastic> list, String source){
        List<Event> result = new ArrayList<>();
        for (EventElastic event : list) {
            Event newEvent = new Event();
            newEvent.id = "fb-" + event.id;
            newEvent.place = event.place;
            newEvent.description = event.description;
            newEvent.startTime = LocalDateTime.parse(event.start_time);
            newEvent.endTime = LocalDateTime.parse(event.end_time);
            newEvent.url = "https://www.facebook.com/events/" + event.id;
            newEvent.eventSourceUrl = "https://www.facebook.com/" + source;
            newEvent.eventType = EventDefaultType.unspecified.toString();   
            result.add(newEvent);
        }
    
        return result;
    }
    
}
