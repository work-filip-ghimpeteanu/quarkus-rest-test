package org.filip.controllers;

import org.filip.config.Configs;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/app")
public class AppController {

    @Inject
    Configs configs;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name")
    public String name() {
        return "{\"name\": \"" + configs.applicationName() + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/controllers-number")
    public String controllersNumber() {
        return "{\"number\": " + configs.controllersNumber() + "}";
    }
}
