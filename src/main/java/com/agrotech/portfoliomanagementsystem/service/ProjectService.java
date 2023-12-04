package com.agrotech.portfoliomanagementsystem.service;

import com.agrotech.portfoliomanagementsystem.enums.ProjectStatus;
import com.agrotech.portfoliomanagementsystem.model.Project;
import com.agrotech.portfoliomanagementsystem.repository.ProjectRepository;
import com.agrotech.portfoliomanagementsystem.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to the {@link Project} entity.
 */
@Service

public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    public ProjectService(ProjectRepository projectRepository, MemberRepository memberRepository) {
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * Retrieves a list of all projects.
     *
     * @return List of {@link Project}.
     */
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     * Retrieves a project by its ID.
     *
     * @param projectId The ID of the project to retrieve.
     * @return The {@link Project} if found, or null if not found.
     */
    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    /**
     * Creates a new project.
     *
     * @param project The {@link Project} to be created.
     * @return The created {@link Project}.
     */
    public Project createProject(Project project) {
        // Implement validation or other business logic if needed
        return projectRepository.save(project);
    }

    /**
     * Updates an existing project.
     *
     * @param projectId The ID of the project to update.
     * @param updatedProject The updated information for the project.
     * @return The updated {@link Project} if found, or null if the project with the given ID is not found.
     */
    public Project updateProject(Long projectId, Project updatedProject) {
        // Implement validation or other business logic if needed
        Optional<Project> existingProject = projectRepository.findById(projectId);
        if (existingProject.isPresent()) {
            updatedProject.setId(projectId);
            return projectRepository.save(updatedProject);
        } else {
            // Handle not found scenario, throw an exception or return null, based on your design
            return null;
        }
    }

    /**
     * Deletes a project by its ID.
     *
     * @param projectId The ID of the project to delete.
     */
    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));

        validateProjectStatusForDeletion(project.getStatus());

        projectRepository.delete(project);
    }

    private void validateProjectStatusForDeletion(ProjectStatus status) {
        if (status == ProjectStatus.STARTED || status == ProjectStatus.IN_PROGRESS || status == ProjectStatus.CLOSED) {
            throw new IllegalStateException("Cannot delete a project with status started, in progress, or closed.");
        }
    }

    // Implement the rest of the methods as per your business requirements
    /**
     * Associates a member with a project.
     *
     * @param projectId The ID of the project.
     * @param personId The ID of the person to associate.
     */
    public void associateMemberWithProject(Long projectId, Long personId) {
        // Implement logic to associate a member with a project
    }

    /**
     * Disassociates a member from a project.
     *
     * @param projectId The ID of the project.
     * @param personId The ID of the person to disassociate.
     */
    public void disassociateMemberFromProject(Long projectId, Long personId) {
        // Implement logic to disassociate a member from a project
    }
}