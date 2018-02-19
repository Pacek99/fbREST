/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import com.mycompany.fbrest.EventRESTDTO;
import com.mycompany.fbrest.models.EventREST;
import com.mycompany.fbrest.models.MergedEvent;
import events.Launcher;
import events.entities.Event;
import events.entities.EventsSimilarity;
import events.entities.SimilarityState;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Patrik Rojek
 */
public class ResolvedSimilaritiesService {

    public void resolveAsEqual(MergedEvent event, String event1Id, String event2Id) {
        EventREST newEvent = new EventREST();
        newEvent.id = event.id;
        newEvent.place = event.place;
        newEvent.description = event.description;
        newEvent.startTime = event.startTime;
        newEvent.endTime = event.endTime;
        newEvent.eventType = event.eventType;
        newEvent.url = event.url;
        newEvent.eventSourceUrl = event.eventSourceUrl;
        
        Collection<String> c = new ArrayList<>();
        c.add(event1Id);
        c.add(event2Id);
        Launcher.eventService.markAsEqual(c, newEvent.id);
        EventsSimilarity es = Launcher.eventsSimilarityService.findByIds(event1Id, event2Id);
        es.similarityState = SimilarityState.MARKED_AS_EQUAL;
        es.lastStateChange = LocalDateTime.now();
        Launcher.eventsSimilarityService.update(es);
        List<EventREST> list = new ArrayList<>();
        list.add(newEvent);
        Collection<Event> e = EventRESTDTO.toEvents(list);
        Launcher.eventService.saveAll(e);
    }

    public void resolveAsSubevents(String parentId, String childId) {
        Launcher.eventService.markAsSubevents(parentId, childId);
        EventsSimilarity es = Launcher.eventsSimilarityService.findByIds(parentId, childId);
        if (es.eventOneId.equals(parentId)) {
            es.similarityState = SimilarityState.MARKED_AS_SECOND_IS_SUBEVENT;
        } else {
            es.similarityState = SimilarityState.MARKED_AS_FIRST_IS_SUBEVENT;
        }        
        es.lastStateChange = LocalDateTime.now();
        Launcher.eventsSimilarityService.update(es);
    }

    public void resolveAsUnrelated(String event1Id, String event2Id) {
        Collection<String> c = new ArrayList<>();
        c.add(event1Id);
        c.add(event2Id);
        Launcher.eventService.markAsUnrelated(c);
        EventsSimilarity es = Launcher.eventsSimilarityService.findByIds(event1Id, event2Id);
        es.similarityState = SimilarityState.MARKED_AS_NOT_RELATED;
        es.lastStateChange = LocalDateTime.now();
        Launcher.eventsSimilarityService.update(es);
    }

}
