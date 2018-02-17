/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import events.Launcher;
import events.entities.Event;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Patrik Rojek
 */

/*tento resource je iba pomocný resource na zistenie či su nejake date v elastic DB, vypis*/
@Path("/control")
public class PomocnyResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Event> getEvents()
    {
        return Launcher.eventService.findAll();
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
}
