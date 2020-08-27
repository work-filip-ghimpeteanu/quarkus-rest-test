package org.filip.services;

import org.filip.resources.Person;

import java.util.List;

public interface PersonService {
    Person getOne(Long id);
    List<Person> getAll();
    String sing(Long id);
}
