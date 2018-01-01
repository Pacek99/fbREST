/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temporary.dataAccess;

import Temporary.commons.SimilarityState;
import Temporary.dataAccess.types.EventsSimilarityElastic;

import java.util.Collection;

/**
 * Represents a service for communicating with data storage of EventsSimilarity objects.
 */
public interface EventsSimilarityService
{
    /**
     * Finds all events similarities that match given criteria.
     *
     * @param criteria The criteria.
     *
     * @return The events similarities.
     */
    Iterable<EventsSimilarityElastic> findAll(EventSimilaritySearchCriteria criteria);

    /**
     * Marks a user hint that given events are equal.
     *
     * @param userId   The user id.
     * @param eventIds The event ids.
     */
    void hintAsEqualEvents(Integer userId, Collection<String> eventIds);

    /**
     * Unmarks a user hint that given events are equal.
     *
     * @param userId   The user id.
     * @param eventIds The event ids.
     */
    void unhintAsEqualEvents(Integer userId, Collection<String> eventIds);

    /**
     * Marks a user hint that given events are sub-events.
     *
     * @param userId      The user id.
     * @param parentEvent The parent event id.
     * @param childEvent  The child event id.
     */
    void hintAsSubevent(Integer userId, String parentEvent, String childEvent);

    /**
     * Unmarks a user hint that given events are sub-events.
     *
     * @param userId      The user id.
     * @param parentEvent The parent event id.
     * @param childEvent  The child event id.
     */
    void unhintAsSubevent(Integer userId, String parentEvent, String childEvent);

    /**
     * Changes the similarity state of a given similarity object.
     *
     * @param similarity The events similarity.
     * @param newState   The new state
     */
    void changeSimilarityState(EventsSimilarityElastic similarity, SimilarityState newState);

    /**
     * Saves or updates a given similarity. The update part is not supposed to be used if we want to change the
     * similarity state. For that, we should use the separate method of EventsSimilarityService.
     *
     * @param similarity The similarity.
     */
    void save(EventsSimilarityElastic similarity);
}
