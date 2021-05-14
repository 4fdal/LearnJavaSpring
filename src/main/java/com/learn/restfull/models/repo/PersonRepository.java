package com.learn.restfull.models.repo;

import com.learn.restfull.models.entities.Person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
