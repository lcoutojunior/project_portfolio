package com.agrotech.portfoliomanagementsystem.repository;

import com.agrotech.portfoliomanagementsystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // You can add custom queries or methods if needed
}