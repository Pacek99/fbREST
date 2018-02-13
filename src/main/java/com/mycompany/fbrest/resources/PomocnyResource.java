/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.LauncherInicializator;
import events.Launcher;
import events.entities.Event;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik Rojek
 */

/*tento resource je iba pomocný resource na zistenie či su nejake date v elastic DB, vypis*/
@Path("/control")
public class PomocnyResource {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Event> getEvents(){
        LauncherInicializator.initLauncher(Launcher.eventService);
        return Launcher.eventService.findAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public long numberOfEventsInDB(){
        LauncherInicializator.initLauncher(Launcher.eventService);
        return Launcher.eventService.findAll().spliterator().getExactSizeIfKnown();
    }
    
}
