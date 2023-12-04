package com.agrotech.portfoliomanagementsystem.service;

import com.agrotech.portfoliomanagementsystem.model.Person;
import com.agrotech.portfoliomanagementsystem.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to the {@link Person} entity.
 */
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    /**
     * Retrieves a list of all persons.
     *
     * @return List of {@link Person}.
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Retrieves a person by their ID.
     *
     * @param personId The ID of the person to retrieve.
     * @return An {@link Optional} containing the {@link Person} if found, or empty if not found.
     */
    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElse(null);
    }

    /**
     * Creates a new person.
     *
     * @param person The {@link Person} to be created.
     * @return The created {@link Person}.
     */
    public Person createPerson(Person person) {
        // Implement your logic for saving a person
        // You might want to validate the input, set default values, etc.
        // For simplicity, let's assume you just save the person to the repository.
        return personRepository.save(person);
    }

    /**
     * Deletes a person by their ID.
     *
     * @param personId The ID of the person to delete.
     */
    public void deletePerson(Long personId) {
        // Implement your logic for deleting a person
        // For simplicity, let's assume you just delete the person from the repository.
        personRepository.deleteById(personId);
    }

    /**
     * Updates an existing person.
     *
     * @param personId The ID of the person to update.
     * @param updatedPerson The updated information for the person.
     * @return The updated {@link Person} if found, or null if the person with the given ID is not found.
     */
    public Person updatePerson(Long personId, Person updatedPerson) {
        // Implement your logic for updating a person
        // Check if the person with the given ID exists, then update its fields
        // For simplicity, let's assume you just update the person in the repository.
        return personRepository.findById(personId)
                .map(person -> {
                    person.setName(updatedPerson.getName());
                    person.setBirthDate(updatedPerson.getBirthDate());
                    person.setCpf(updatedPerson.getCpf());
                    person.setEmployee(updatedPerson.isEmployee());
                    person.setManager(updatedPerson.isManager());
                    return personRepository.save(person);
                })
                .orElse(null); // Or throw an exception if the person is not found
    }
}