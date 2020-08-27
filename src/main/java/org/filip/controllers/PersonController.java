package org.filip.controllers;

import org.filip.resources.Person;
import org.filip.services.PersonService;

import java.util.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
public class PersonController {

    @Inject
    PersonService personService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Person read(@PathParam("id") Long id) {
        return personService.getOne(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> readAll() {
        return personService.getAll();
    }

    @Transactional
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Person create(Person person) {
        return personService.addOne(person);
    }

    @Transactional
    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Person delete(@PathParam("id") Long id) {
        return personService.deleteOne(id);
    }

    @Transactional
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{id}")
    public Person update(@PathParam("id") Long id, Person person) {
        return personService.updateOne(id, person);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}/sing")
    public String sing(@PathParam("id") Long id) {
        return personService.sing(id);
    }
}
