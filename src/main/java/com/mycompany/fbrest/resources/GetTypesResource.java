/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.models.Types;
import eventagent.persistence.entities.EventDefaultType;
import eventagent.persistence.entities.SourceType;
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
        return new Types();
    }
    
    /*
    public static void main(String[] args) {
        SourceType[] aaa = SourceType.values();
        EventDefaultType[] bbb = EventDefaultType.values();
        Object[] o = new Object[2];
        o[0] = aaa;
        o[1] = bbb;
        System.out.println(o);
    }*/
}
