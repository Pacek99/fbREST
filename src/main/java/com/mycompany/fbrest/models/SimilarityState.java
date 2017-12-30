/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

/**
 * Represents in which state a given potential event similarity is
 * from the admin's perspective.
 */
public enum SimilarityState
{
    /**
     * Indicates that the similarity hasn't been resolved yet.
     */
    NOT_RESOLVED,

    /**
     * Indicates that given events have been resolved as equal.
     */
    MARKED_AS_EQUAL,

    /**
     * Indicates that the first event has been resolved as a sub-event of the second one.
     */
    MARKED_AS_FIRST_IS_SUBEVENT,

    /**
     * Indicates that the second event has been resolved as a sub-event of the first one.
     */
    MARKED_AS_SECOND_IS_SUBEVENT,

    /**
     * Indicates that the giv events have been resolved as not-related to each other.
     */
    NOT_RELATED
}