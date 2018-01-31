/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.models.EventSource;
import com.mycompany.fbrest.services.EventSourceService;
import eventagent.persistence.entities.EventsSource;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik Rojek
 */
@Path("/eventsource")
public class EventSourceResouce {
    
    EventSourceService ess = new EventSourceService();
    
    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    public void addSource(EventSource source){
        ess.addNewSource(source); 
    }
   
    @PUT
    @Path("/{source}/{newFrequency}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateFrequency(@PathParam("source") String source,
                                @PathParam("newFrequency") Double newFrequency){
        ess.updateFrequency(source,newFrequency); 
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventsSource> getSources(){
        return ess.getSources();
    } 
    
    @DELETE
    @Path("/{source}")
    public void deleteSubject(@PathParam("source") String source){
        ess.deleteSource(source);
    }    
}   