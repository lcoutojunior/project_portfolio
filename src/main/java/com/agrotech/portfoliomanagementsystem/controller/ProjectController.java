package com.agrotech.portfoliomanagementsystem.controller;

import com.agrotech.portfoliomanagementsystem.model.Project;
import com.agrotech.portfoliomanagementsystem.service.ProjectService;
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
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Retrieve a list of all projects.
     *
     * @return ResponseEntity containing the list of projects
     */
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /**
     * Create a new project.
     *
     * @param project The project to be created
     * @return ResponseEntity containing the created project
     */
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    /**
     * Retrieve a project by its ID.
     *
     * @param projectId ID of the project to retrieve
     * @return ResponseEntity containing the retrieved project
     */
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    /**
     * Update a project with the given ID.
     *
     * @param projectId ID of the project to be updated
     * @param updatedProject The updated project data
     * @return ResponseEntity containing the updated project
     */
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project updatedProject) {
        Project project = projectService.updateProject(projectId, updatedProject);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    /**
     * Delete a project by its ID.
     *
     * @param projectId ID of the project to be deleted
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}