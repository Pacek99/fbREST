/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

import events.entities.Event;
import events.entities.SimilarityState;
import java.util.HashSet;

/**
 *
 * @author Patrik Rojek
 */
public class UnsolvedSimilarity {
     /**
     * The id of this similarity. This id will be set within the EventsSimilarityService.
     */
    public String id;

    /**
     * The id of the first concerned event.
     */
    public EventREST eventOne;

    /**
     * The id of the second concerned event.
     */
    public EventREST eventTwo;

    /**
     * The ids of the users that suggest that the given two events are equal. The value is not null.
     */
    public HashSet<String> equalitySuggestions = new HashSet<>();

    /**
     * The ids of the users that suggest that the first event is a sub-event of the second one. The value is not null.
     */
    public HashSet<String> firstIsSubeventSuggestions = new HashSet<>();

    /**
     * The ids of the users that suggest that the second event is a sub-event of the first one. The value is not null.
     */
    public HashSet<String> secondIsSubeventSuggestions = new HashSet<>();

    /**
     * The calculated probability that the given events are equal.
     */
    public double probabilityOfEquality;

    /**
     * The state in which this similarity is currently resolved.
     */
    public SimilarityState similarityState;
}
