/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.services.HintAsService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik Rojek
 */
@Path("/hintAsSubevent")
public class HintAsSubeventResource {
    
    HintAsService has = new HintAsService();

    @GET
    @Path("/{userId}/{parentId}/{childId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void hintAsEqual(@PathParam("userId") String userId,
                            @PathParam("parentId") String parentId,
                            @PathParam("childId") String childId){
        has.hintAsSubevent(userId,parentId,childId);
    }
}
