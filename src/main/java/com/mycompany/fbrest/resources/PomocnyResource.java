/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.services.GraphAPIService;
import events.Launcher;
import events.entities.Event;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import javax.ws.rs.PUT;
import jersey.repackaged.com.google.common.collect.Lists;
import org.json.JSONException;

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

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String numberOfEventsInDB()
    {
        try
        {
            // Prepare some super-cool events
            Event e1 = new Event();
            e1.id = "1";
            e1.description = "Event1";
            Event e2 = new Event();
            e2.id = "2";
            e2.description = "Event2";
            Event e3 = new Event();
            e3.id = "3";
            e3.description = "Event3";

            // Clear the content of the database
            Launcher.initializer.reinitialize();

            // Save the events
            Launcher.eventService.saveAll(Arrays.asList(e1, e2, e3));

            // Calculate their count
            AtomicInteger eventsCount = new AtomicInteger();
            Launcher.eventService.findAll().forEach(e -> eventsCount.incrementAndGet());

            // Add some similarities
            Launcher.eventsSimilarityService.saveNew(e1.id, e2.id, 0.5);
            Launcher.eventsSimilarityService.saveNew(e2.id, e3.id, 0.5);

            AtomicInteger unresolvedCount = new AtomicInteger();
            Launcher.eventsSimilarityService.findAllUnresolved().forEach(s -> unresolvedCount.incrementAndGet());

            // Try even the calculator of probability (if it's not null)
            double coefficient = Launcher.similarityCalculator.calculateSimilarityCoefficient(e1, e2);

            // Return string with tis information to verify
            return "OK " + eventsCount.toString() + " " + coefficient + " " + unresolvedCount;
        }
        catch (Exception e)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        }
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
}
