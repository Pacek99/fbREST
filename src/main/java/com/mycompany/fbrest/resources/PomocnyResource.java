/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.services.GraphAPIService;
import events.Launcher;
import events.entities.Event;
import jersey.repackaged.com.google.common.collect.Lists;
import org.json.JSONException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrik Rojek
 */

/*tento resource je iba pomocný resource na zistenie či su nejake date v elastic DB, vypis*/
@Path("/control")
public class PomocnyResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEvents()
    {
        return Lists.newArrayList(Launcher.eventService.findAll());
    }

    @PUT
    public void downloadEvents() throws JSONException
    {
        List<Event> allRecievedEvents = GraphAPIService.getEventsFromSource("331514527167");
        List<Event> newRecievedEvents = new ArrayList<>();
        for (Event recievedEvent : allRecievedEvents) {
            if (!Launcher.eventService.existsById(recievedEvent.id)) {
                newRecievedEvents.add(recievedEvent);
            }
        }
        //3. Vytiahneš všetky staré z databázy (najskôr všetky, tu si nie som istý).
        Iterable<Event> eventsFromDB = Launcher.eventService.findAll();

        //4. Uložíš ešte aj nové eventy...
        Launcher.eventService.saveAll(newRecievedEvents);

        //5. Vytvoríš dvojice nových a starých + dvojice nových navzájom. 
        // + aj 6. Uložíš všetky dvojice dvojice ako EventsSimilarities.
        for (Event newE : newRecievedEvents) {
            for (Event e : eventsFromDB) {
                Launcher.eventsSimilarityService.saveNew(e.id, newE.id, Launcher.similarityCalculator.calculateSimilarityCoefficient(e, newE));
            }
        }

        // tu este treba dvojice novych navzajom
        for (int i = 0; i < newRecievedEvents.size(); i++) {
            for (int j = i + 1; j < newRecievedEvents.size(); j++) {
                Launcher.eventsSimilarityService.saveNew(newRecievedEvents.get(i).id, newRecievedEvents.get(j).id, Launcher.similarityCalculator.calculateSimilarityCoefficient(newRecievedEvents.get(i), newRecievedEvents.get(j)));
            }
        }        
    }
    
    @DELETE
    public void reinitializeDB() {
        Launcher.initializer.reinitialize();
    }
}
