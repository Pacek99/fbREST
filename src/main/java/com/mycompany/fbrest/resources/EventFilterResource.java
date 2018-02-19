/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.models.EventREST;
import com.mycompany.fbrest.services.EventFilterService;
import events.entities.Event;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik Rojek
 */
@Path("/filter")
public class EventFilterResource {
    
    EventFilterService filterService = new EventFilterService();
    
    @GET
    @Path("/{latitude}/{longitude}/{radius}/{startDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventREST> filterEvents(@PathParam("latitude") Double latitude,
                                    @PathParam("longitude") Double longitude,
                                    @PathParam("radius") Double radius,
                                    @PathParam("startDate") String startDate){
                
        return filterService.filter(latitude,longitude,radius,startDate); 
    }
}
