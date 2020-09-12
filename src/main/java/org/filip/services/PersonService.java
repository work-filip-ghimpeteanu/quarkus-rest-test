package org.filip.services;

import org.filip.resources.PersonResource;

import java.util.List;

public interface PersonService {
    PersonResource getOne(Long id);
    List<PersonResource> getAll();
    PersonResource addOne(PersonResource person);
    PersonResource deleteOne(Long id);
    PersonResource updateOne(Long id, PersonResource person);
    String sing(Long id);
}
