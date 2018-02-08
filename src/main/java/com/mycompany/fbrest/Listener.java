/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest;

import eventagent.persistence.entities.EventsSource;

/**
 *
 * @author Patrik Rojek
 */
public interface Listener {
    
    void hotovo(EventsSource es);
}
