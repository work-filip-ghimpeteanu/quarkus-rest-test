package org.filip.controllers;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.*;
import org.filip.resources.PersonResource;
import org.filip.services.PersonService;

import java.util.List;

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
    @Counted(
            name = "readAccruedRequests",
            description = "How many reads have been performed so far."
    )
    @Timed(
            name = "readTimeElapseInMillis",
            description = "How long it takes to do a read",
            unit = MetricUnits.MILLISECONDS
    )
    public PersonResource read(@PathParam("id") Long id) {
        return personService.getOne(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonResource> readAll() {
        return personService.getAll();
    }

    @Transactional
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Counted(
            name = "createAccruedRequests",
            description = "How many create have been performed so far."
    )
    @Timed(
            name = "createTimeElapseInMillis",
            description = "How long it takes to do a create",
            unit = MetricUnits.MILLISECONDS
    )
    public PersonResource create(PersonResource person) {
        return personService.addOne(person);
    }

    @Transactional
    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public PersonResource delete(@PathParam("id") Long id) {
        return personService.deleteOne(id);
    }

    @Transactional
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{id}")
    public PersonResource update(@PathParam("id") Long id, PersonResource person) {
        return personService.updateOne(id, person);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}/sing")
    public String sing(@PathParam("id") Long id) {
        return personService.sing(id);
    }
}
