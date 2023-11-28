package com.agrotech.repository;

import com.agrotech.model.ProjectModel;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectModel, Long> {
    // Custom queries if needed
}
