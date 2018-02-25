/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest;

import com.mycompany.fbrest.models.EventForFilter;
import com.mycompany.fbrest.models.EventREST;
import com.mycompany.fbrest.models.LocationREST;
import com.mycompany.fbrest.models.PlaceREST;
import events.Launcher;
import java.util.ArrayList;
import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;

/**
 *
 * @author Patrik Rojek
 */
public class EventForFilterDTO {

    public static List<EventForFilter> transform(List<EventREST> list) {
        List<EventForFilter> result = new ArrayList<>();
        for (EventREST event : list) {
            EventForFilter eff = new EventForFilter();
            eff.id = event.id;
            eff.name = event.name;
            eff.place = event.place;
            eff.description = event.description;
            eff.startTime = event.startTime;
            eff.endTime = event.endTime;
            eff.eventType = event.eventType;
            eff.url = event.url;
            eff.eventSourceUrl = event.eventSourceUrl;
            eff.equalEvents = event.equalEvents;
            eff.mergedEventId = event.mergedEventId;
            eff.subEvents = new ArrayList<>();
            if (!event.subEvents.isEmpty()) {
                eff.subEvents = EventRESTDTO.transform(Lists.newArrayList(Launcher.eventService.findAllById(event.subEvents)));
            }            
            eff.parentEventId = event.parentEventId;

            result.add(eff);
        }
        return result;
    }
}
