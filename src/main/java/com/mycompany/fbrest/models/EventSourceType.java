/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

/**
 * Represents the type of an event source (it might be
 * a FB page / user / group / website in the future)
 */
public enum EventSourceType
{
    /**
     * The facebook page source
     */
    PAGE,

    /**
     * The facebook group source
     */
    GROUP,

    /**
     * The facebook user source
     */
    USER
}
