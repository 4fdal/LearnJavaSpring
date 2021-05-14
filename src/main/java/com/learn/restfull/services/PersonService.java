package com.learn.restfull.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.learn.restfull.models.entities.Person;
import com.learn.restfull.models.repo.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person create(Person person) {
        return this.personRepository.save(person);
    }

    public Person update(Person person) {
        return this.personRepository.save(person);
    }

    public Iterable<Person> getAll() {
        return this.personRepository.findAll();
    }

    public Person get(long id) {

        Optional<Person> person = this.personRepository.findById(id);
        if (!person.isPresent()) {
            return null;
        }

        return person.get();
    }

    public void delete(long id) {
        this.personRepository.deleteById(id);
    }
}
