/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.models.Types;
import eventagent.persistence.entities.EventDefaultType;
import eventagent.persistence.entities.SourceType;
import java.util.stream.Stream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik Rojek
 */

@Path("/getTypes")
public class GetTypesResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Types getTypes(){
        Types t = new Types();
        t.setSourceTypes(Stream.of(SourceType.values()).map(SourceType::name).toArray(String[]::new));
        t.setEventDefaultTypes(Stream.of(EventDefaultType.values()).map(EventDefaultType::name).toArray(String[]::new));
        return t;
    }
}
