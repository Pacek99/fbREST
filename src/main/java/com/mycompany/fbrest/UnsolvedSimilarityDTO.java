/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest;

import com.mycompany.fbrest.models.EventREST;
import com.mycompany.fbrest.models.UnsolvedSimilarity;
import events.Launcher;
import events.entities.EventsSimilarity;
import java.util.ArrayList;
import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;

/**
 *
 * @author Patrik Rojek
 */
public class UnsolvedSimilarityDTO {
 
    public static List<UnsolvedSimilarity> transform(List<EventsSimilarity> list){
        List<UnsolvedSimilarity> result = new ArrayList<>();
        List<EventREST> events;
        for (EventsSimilarity es : list) {
            UnsolvedSimilarity us = new UnsolvedSimilarity();
            us.id = es.id;
            List<String> ids = new ArrayList<>();
            ids.add(es.eventOneId);
            ids.add(es.eventTwoId);
            events = EventRESTDTO.transform(Lists.newArrayList(Launcher.eventService.findAllById(ids)));
            if (events.get(0).id.equals(es.eventOneId)) {
                us.eventOne = events.get(0);
                us.eventTwo = events.get(1);
            } else {
                us.eventOne = events.get(1);
                us.eventTwo = events.get(0);
            }
            us.equalitySuggestions = es.equalitySuggestions;
            us.firstIsSubeventSuggestions = es.firstIsSubeventSuggestions;
            us.secondIsSubeventSuggestions = es.secondIsSubeventSuggestions;
            us.probabilityOfEquality = es.probabilityOfEquality;
            us.similarityState = es.similarityState;
            result.add(us);
        }
                
        return result;
    }
}
