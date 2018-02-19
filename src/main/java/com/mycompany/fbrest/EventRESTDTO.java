/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest;

import com.mycompany.fbrest.models.EventREST;
import com.mycompany.fbrest.models.LocationREST;
import com.mycompany.fbrest.models.PlaceREST;
import events.entities.Event;
import events.entities.Location;
import events.entities.Place;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 *
 * @author Patrik Rojek
 */
public class EventRESTDTO {

    public static List<EventREST> transform(List<Event> list) {
        List<EventREST> result = new ArrayList<>();
        for (Event event : list) {
            LocationREST lrest = new LocationREST();
            lrest.city = event.place.location.city;
            lrest.country = event.place.location.country;
            lrest.latitude = event.place.location.coordinates.getLat();
            lrest.longitude = event.place.location.coordinates.getLon();
            lrest.street = event.place.location.street;
            lrest.zip = event.place.location.zip;

            PlaceREST prest = new PlaceREST();
            prest.name = event.place.name;
            prest.location = lrest;

            EventREST erest = new EventREST();
            erest.id = event.id;
            erest.place = prest;
            erest.description = event.description;
            erest.startTime = event.startTime;
            erest.endTime = event.endTime;
            erest.eventType = event.eventType;
            erest.url = event.url;
            erest.eventSourceUrl = event.eventSourceUrl;
            erest.equalEvents = event.equalEvents;
            erest.mergedEventId = event.mergedEventId;
            erest.subEvents = event.subEvents;
            erest.parentEventId = event.parentEventId;

            result.add(erest);
        }
        return result;
    }

    public static List<Event> toEvents(List<EventREST> erests) {
        List<Event> result = new ArrayList<>();
        for (EventREST erest : erests) {
            Location l = new Location();
            l.city = erest.place.location.city;
            l.country = erest.place.location.country;
            l.coordinates = new GeoPoint(erest.place.location.latitude, erest.place.location.longitude);
            l.street = erest.place.location.street;
            l.zip = erest.place.location.zip;

            Place p = new Place();
            p.name = erest.place.name;
            p.location = l;

            Event e = new Event();
            e.id = erest.id;
            e.place = p;
            e.description = erest.description;
            e.startTime = erest.startTime;
            e.endTime = erest.endTime;
            e.eventType = erest.eventType;
            e.url = erest.url;
            e.eventSourceUrl = erest.eventSourceUrl;
            e.equalEvents = erest.equalEvents;
            e.mergedEventId = erest.mergedEventId;
            e.subEvents = erest.subEvents;
            e.parentEventId = erest.parentEventId;
            
            result.add(e);
        }
        return result;
    }
}
