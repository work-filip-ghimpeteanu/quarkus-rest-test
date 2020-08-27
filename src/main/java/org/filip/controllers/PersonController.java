package org.filip.controllers;

import org.filip.resources.Person;
import org.filip.services.PersonService;

import java.util.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
public class PersonController {

    @Inject
    PersonService personService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Person get(@PathParam("id") Long id) {
        return personService.getOne(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> list() {
        return personService.getAll();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}/sing")
    public String sing(@PathParam("id") Long id) {
        return personService.sing(id);
    }
}
