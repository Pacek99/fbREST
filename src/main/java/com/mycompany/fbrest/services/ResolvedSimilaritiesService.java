/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import events.Launcher;
import events.entities.Event;
import events.entities.EventsSimilarity;
import events.entities.SimilarityState;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Patrik Rojek
 */
public class ResolvedSimilaritiesService {

    public void resolveAsEqual(Event newEvent, String event1Id, String event2Id) {
        Collection<String> c = new ArrayList<>();
        c.add(event1Id);
        c.add(event2Id);
        Launcher.eventService.markAsEqual(c, event1Id);
        EventsSimilarity es = Launcher.eventsSimilarityService.findByIds(event1Id, event2Id);
        es.similarityState = SimilarityState.MARKED_AS_EQUAL;
        es.lastStateChange = LocalDateTime.now();
        Launcher.eventsSimilarityService.update(es);
        Collection<Event> e = new ArrayList<>();
        e.add(newEvent);
        Launcher.eventService.saveAll(e);
    }

    public void resolveAsSubevents(String parentId, String childId) {
        Launcher.eventService.markAsSubevents(parentId, childId);
        EventsSimilarity es = Launcher.eventsSimilarityService.findByIds(parentId, childId);
        es.similarityState = SimilarityState.MARKED_AS_SECOND_IS_SUBEVENT;
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
