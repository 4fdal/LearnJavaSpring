package com.learn.restfull.controllers;

import javax.validation.Valid;

import com.learn.restfull.dto.PersonData;
import com.learn.restfull.dto.ResponseData;
import com.learn.restfull.models.entities.Person;
import com.learn.restfull.services.PersonService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;
    // busa di panggil saja karena telah di iniliztion di root.java project nya

    @PostMapping
    public ResponseEntity<ResponseData<Person>> create(@Valid @RequestBody PersonData personData, Errors errors) {

        ResponseData<Person> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // Person person = new Person(personData.getName(), personData.getEmail(),
        // personData.getEmail());
        Person person = modelMapper.map(personData, Person.class);
        // harus ada kemiripan object variable untuk dapat conversi dari person_data ke
        // class person

        responseData.setStatus(true);
        responseData.setPayload(this.personService.create(person));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Person>>> getAll() {

        Iterable<Person> persons = this.personService.getAll();

        ResponseData<Iterable<Person>> responseData = new ResponseData<>();
        responseData.setPayload(persons);
        responseData.setStatus(true);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Person>> get(@PathVariable(name = "id") long id) {
        ResponseData<Person> responseData = new ResponseData<>();
        
        responseData.setPayload(this.personService.get(id));
        responseData.setStatus(true);

        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Person>> update(@Valid @RequestBody PersonData personData, Errors errors){
        ResponseData<Person> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Person person = this.modelMapper.map(personData, Person.class);

        responseData.setStatus(true);
        responseData.setPayload(this.personService.update(person));

        return ResponseEntity.ok(responseData) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> delete(@PathVariable(name = "id") long id){

        ResponseData<String> responseData = new ResponseData<>();
        this.personService.delete(id);
        responseData.setStatus(true);
        
        return ResponseEntity.ok(responseData);
    }
}
