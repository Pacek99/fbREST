/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.resources;

import com.mycompany.fbrest.services.AdminService;
import eventagent.persistence.entities.Admin;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik Rojek
 */
@Path("/admin")
public class AdminResource {
    
    AdminService as = new AdminService();

    @GET
    @Path("/{admin}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean isAdmin(@PathParam("admin") String admin){
        return as.isAdmin(admin);
    }      
}