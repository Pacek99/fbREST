/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.services.ResolvedSimilaritiesService;
import events.entities.Event;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik Rojek
 */
@Path("/resolvedSimilarities")
public class ResolvedSimilaritiesResource {
    
        
    ResolvedSimilaritiesService rss = new ResolvedSimilaritiesService();
    
    @POST
    @Path("/{event1Id}/{event2Id}") 
    @Consumes(MediaType.APPLICATION_JSON)
    public void resolveAsEqual(Event newEvent,
                               @PathParam("event1Id") String event1Id,
                               @PathParam("event2Id") String event2Id){
        rss.resolveAsEqual(newEvent, event1Id, event2Id); 
    }
    
   @GET
    @Path("/{parentId}/{childId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void resolveAsSubevents(@PathParam("parentId") String parentId,
                            @PathParam("childId") String childId){
        rss.resolveAsSubevents(parentId, childId);
    } 
}
