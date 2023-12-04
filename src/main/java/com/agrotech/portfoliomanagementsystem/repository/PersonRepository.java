package com.agrotech.portfoliomanagementsystem.repository;

import com.agrotech.portfoliomanagementsystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // You can add custom query methods here if needed
}