package org.filip.services.impl;

import io.quarkus.panache.common.Sort;
import org.filip.RandomLoadApplier;
import org.filip.entities.Person;
import org.filip.exceptions.RestException;
import org.filip.repository.PersonRepository;
import org.filip.resources.PersonResource;
import org.filip.services.PersonService;

import java.util.*;
import java.util.function.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class PersonServiceImpl implements PersonService {

    @Inject
    PersonRepository personRepository;
    @Inject
    RandomLoadApplier randomLoadApplier;

    @Override
    public PersonResource getOne(Long id) {
        Person person = personRepository
                .findByIdOptional(id)
                .orElseThrow(personNotFound(id));

        randomLoadApplier.applyALoad();
        return buildPersonResource(person);
    }

    @Override
    public List<PersonResource> getAll() {
        List<Person> persons = personRepository.listAll(Sort.by("id"));
        List<PersonResource> personResources = new LinkedList<>();
        for (Person person : persons) {
            personResources.add(buildPersonResource(person));
        }
        return personResources;
    }

    @Override
    public PersonResource addOne(PersonResource personResource) {
        personRepository.persist(buildPerson(personResource));

        randomLoadApplier.applyALoad();
        return personResource;
    }

    private Person buildPerson(PersonResource personResource) {
        Person person = new Person();
        person.setName(personResource.getName());
        person.setAge(personResource.getAge());
        return person;
    }

    @Override
    public PersonResource deleteOne(Long id) {
        Person person = personRepository
                .findByIdOptional(id)
                .orElseThrow(personNotFound(id));

        personRepository.delete(person);
        return buildPersonResource(person);
    }

    private PersonResource buildPersonResource(Person person) {
        PersonResource personResource = new PersonResource();
        personResource.setId(person.getId());
        personResource.setName(person.getName());
        personResource.setAge(person.getAge());
        return personResource;
    }

    @Override
    public PersonResource updateOne(Long id, PersonResource personResource) {
        Person toUpdate = personRepository
                .findByIdOptional(id)
                .orElseThrow(personNotFound(id));

        toUpdate.setName(personResource.getName());
        toUpdate.setAge(personResource.getAge());
        personRepository.update(toUpdate);

        personResource.setId(id);
        return personResource;
    }

    @Override
    public String sing(Long id) {
        Person person = personRepository
                .findByIdOptional(id)
                .orElseThrow(personNotFound(id));

        return person + " sings: Lalalala";
    }

    private Supplier<RestException> personNotFound(Long id) {
        return () -> new RestException(Response.Status.NOT_FOUND, "Could not find Person with id " + id);
    }
}
