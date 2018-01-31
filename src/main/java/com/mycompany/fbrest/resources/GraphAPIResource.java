/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.services.GraphAPIService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik Rojek
 */
@Path("/graphapi")
public class GraphAPIResource {
    
    GraphAPIService gapis = new GraphAPIService();

    @GET
    @Path("/{source}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateSource(@PathParam("source") String source){
        gapis.getEventsFromSource(source);
    }
}
