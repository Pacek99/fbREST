/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.services.HintAsService;
import events.entities.EventsSimilarity;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik Rojek
 */
@Path("/unsolvedSimilarities")
public class UnsolvedSimilaritiesResource {
    
    HintAsService has = new HintAsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<EventsSimilarity> getUnsolvedSimilarities(){
        return has.getUnsolvedSimilarities();
    }
}
