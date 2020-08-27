package org.filip.controller;

import org.filip.resources.Person;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
public class PersonController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Person get(@PathParam("id") Long id) {
        Person person = new Person();

        person.setName("Filip");
        person.setAge(30);
        person.setId(id);

        return person;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}/sing")
    public String sing(@PathParam("id") Long id) {
        return "Lalalala";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> list(@PathParam("id") Long id) {
        return Collections.singletonList(get(id));
    }
}
