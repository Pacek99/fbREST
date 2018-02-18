/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import com.mycompany.fbrest.UnsolvedSimilarityDTO;
import com.mycompany.fbrest.models.UnsolvedSimilarity;
import events.Launcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jersey.repackaged.com.google.common.collect.Lists;

/**
 *
 * @author Patrik Rojek
 */
public class HintAsService {

    public void hintAsEqual(String userId, String event1Id, String event2Id) {
        Collection<String> eventIds = new ArrayList<>();
        eventIds.add(event1Id);
        eventIds.add(event2Id);
        Launcher.eventsSimilarityService.hintAsEqualEvents(userId, eventIds);
    }
    
    public void hintAsSubevent(String userId, String parentId, String childId) {
        Launcher.eventsSimilarityService.hintAsSubevent(userId, parentId, childId);
    }    

    public List<UnsolvedSimilarity> getUnsolvedSimilarities() {
        return UnsolvedSimilarityDTO.transform(Lists.newArrayList(Launcher.eventsSimilarityService.findAllUnresolved()));
    }
}
