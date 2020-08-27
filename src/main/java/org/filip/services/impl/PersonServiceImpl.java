package org.filip.services.impl;

import org.filip.resources.Person;
import org.filip.services.PersonService;

import java.util.*;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonServiceImpl implements PersonService {

    List<Person> personList = new LinkedList<>();

    public PersonServiceImpl() {
        Person person = new Person();

        person.setName("Filip");
        person.setAge(30);
        person.setId(1L);

        personList.add(person);
    }

    @Override
    public Person getOne(Long id) {
        return personList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(new Person());
    }

    @Override
    public List<Person> getAll() {
        return personList;
    }

    @Override
    public String sing(Long id) {
        Person person = personList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(new Person());

        return person + " sings: Lalalala";
    }
}
