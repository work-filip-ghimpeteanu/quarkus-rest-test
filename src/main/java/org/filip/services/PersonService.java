package org.filip.services;

import org.filip.resources.Person;

import java.util.List;

public interface PersonService {
    Person getOne(Long id);
    List<Person> getAll();
    Person addOne(Person person);
    Person deleteOne(Long id);
    Person updateOne(Long id, Person person);
    String sing(Long id);
}
