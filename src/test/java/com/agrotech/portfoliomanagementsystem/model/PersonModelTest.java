package com.agrotech.portfoliomanagementsystem.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonModelTest {

    @Test
    void testPersonModelCreation() {
        // Given
        Long id = 1L;
        String name = "John Doe";
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String cpf = "12345678901";
        boolean isEmployee = true;
        boolean isManager = false;

        // When
        Person person = new Person(id, name, birthDate, cpf, isEmployee, isManager);

        // Then
        assertEquals(id, person.getId());
        assertEquals(name, person.getName());
        assertEquals(birthDate, person.getBirthDate());
        assertEquals(cpf, person.getCpf());
        assertEquals(isEmployee, person.isEmployee());
        assertEquals(isManager, person.isManager());
    }
}

