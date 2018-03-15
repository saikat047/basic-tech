package org.test.fun.repo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.test.fun.model.Person;

@Component
public class PersonRepository {

    private Map<String, Person> personMap = new HashMap<>();

    public PersonRepository() {
    }

    public void create(Person person) {
        person.setId(Long.toString(System.currentTimeMillis()));
        personMap.put(person.getId(), person);
    }

    public Person get(String id) {
        return personMap.get(id);
    }
}
