package com.agrotech.portfoliomanagementsystem.controller;

import com.agrotech.portfoliomanagementsystem.model.Person;
import com.agrotech.portfoliomanagementsystem.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    /**
     * Retrieve a list of all persons.
     *
     * @return ResponseEntity containing the list of persons
     */
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    /**
     * Create a new person.
     *
     * @param person The person to be created
     * @return ResponseEntity containing the created person
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    /**
     * Retrieve a person by their ID.
     *
     * @param personId ID of the person to retrieve
     * @return ResponseEntity containing the retrieved person
     */
    @GetMapping("/{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long personId) {
        Person person = personService.getPersonById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /**
     * Update a person with the given ID.
     *
     * @param personId ID of the person to be updated
     * @param updatedPerson The updated person data
     * @return ResponseEntity containing the updated person
     */
    @PutMapping("/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long personId, @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(personId, updatedPerson);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /**
     * Delete a person by their ID.
     *
     * @param personId ID of the person to be deleted
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}