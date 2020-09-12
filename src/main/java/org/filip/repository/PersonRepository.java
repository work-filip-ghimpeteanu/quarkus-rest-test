package org.filip.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.filip.entities.Person;

import java.sql.Timestamp;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public Person update(Person person) {
        person.setLastUpdatedOn(new Timestamp(System.currentTimeMillis()));
        return getEntityManager().merge(person);
    }
}
