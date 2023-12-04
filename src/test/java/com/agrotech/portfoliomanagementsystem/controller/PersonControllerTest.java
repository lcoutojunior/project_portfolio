package com.agrotech.portfoliomanagementsystem.controller;

import com.agrotech.portfoliomanagementsystem.model.Person;
import com.agrotech.portfoliomanagementsystem.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PersonControllerTest {

    // Inject mocks into the controller
    @InjectMocks
    private PersonController personController;

    // Mock the service dependency
    @Mock
    private PersonService personService;

    // Initialize the mocks
    public PersonControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPersons() {
        // Arrange: Mock data
        Person person1 = new Person(1L, "John Doe", LocalDate.now(), "12345678901", true, false);
        Person person2 = new Person(2L, "Jane Doe", LocalDate.now(), "98765432101", true, true);
        List<Person> persons = Arrays.asList(person1, person2);

        // Mock service behavior
        when(personService.getAllPersons()).thenReturn(persons);

        // Act: Call the controller method
        ResponseEntity<List<Person>> responseEntity = personController.getAllPersons();

        // Assert: Check the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(persons, responseEntity.getBody());

        // Verify that the service method was called
        verify(personService, times(1)).getAllPersons();
    }

    @Test
    public void testCreatePerson() {
        // Arrange: Mock data
        Person personToCreate = new Person(null, "John Doe", LocalDate.now(), "12345678901", true, false);
        Person createdPerson = new Person(1L, "John Doe", LocalDate.now(), "12345678901", true, false);

        // Mock service behavior
        when(personService.createPerson(personToCreate)).thenReturn(createdPerson);

        // Act: Call the controller method
        ResponseEntity<Person> responseEntity = personController.createPerson(personToCreate);

        // Assert: Check the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(createdPerson, responseEntity.getBody());

        // Verify that the service method was called
        verify(personService, times(1)).createPerson(personToCreate);
    }

    @Test
    public void testUpdatePerson() {
        // Arrange: Mock data
        Long personId = 1L;
        Person updatedPersonData = new Person(null, "Updated Name", LocalDate.now(), "98765432101", false, true);
        Person updatedPerson = new Person(personId, "Updated Name", LocalDate.now(), "98765432101", false, true);

        // Mock service behavior
        when(personService.updatePerson(personId, updatedPersonData)).thenReturn(updatedPerson);

        // Act: Call the controller method
        ResponseEntity<Person> responseEntity = personController.updatePerson(personId, updatedPersonData);

        // Assert: Check the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedPerson, responseEntity.getBody());

        // Verify that the service method was called
        verify(personService, times(1)).updatePerson(personId, updatedPersonData);
    }

    @Test
    public void testDeletePerson() {
        // Arrange: Mock data
        Long personId = 1L;

        // Act: Call the controller method
        ResponseEntity<Void> responseEntity = personController.deletePerson(personId);

        // Assert: Check the response
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verify that the service method was called
        verify(personService, times(1)).deletePerson(personId);
    }

    @Test
    public void testGetPersonById() {
        // Arrange: Mock data
        Long personId = 1L;
        Person person = new Person(personId, "John Doe", LocalDate.now(), "12345678901", true, false);

        // Mock service behavior
        when(personService.getPersonById(personId)).thenReturn(person);

        // Act: Call the controller method
        ResponseEntity<Person> responseEntity = personController.getPersonById(personId);

        // Assert: Check the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(person, responseEntity.getBody());

        // Verify that the service method was called
        verify(personService, times(1)).getPersonById(personId);
    }

    @Test
    public void testGetPersonById_NotFound() {
        // Arrange: Mock data
        Long personId = 1L;

        // Mock service behavior for a non-existing person
        when(personService.getPersonById(personId)).thenReturn(null);

        // Act & Assert: Attempt to get a non-existing person, expect an exception
        assertThrows(IllegalArgumentException.class, () -> personController.getPersonById(personId));
    }
}