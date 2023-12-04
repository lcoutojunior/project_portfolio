package com.agrotech.portfoliomanagementsystem.controller;

import com.agrotech.portfoliomanagementsystem.enums.ProjectRisk;
import com.agrotech.portfoliomanagementsystem.enums.ProjectStatus;
import com.agrotech.portfoliomanagementsystem.model.Person;
import com.agrotech.portfoliomanagementsystem.model.Project;
import com.agrotech.portfoliomanagementsystem.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @Test
    public void testCreateProject() {

        // Creating a sample manager (Person)
        Person manager = new Person();
        manager.setId(1L);
        manager.setName("John Doe");
        manager.setBirthDate(LocalDate.of(1980, 1, 15));
        manager.setCpf("12345678901");
        manager.setEmployee(true);
        manager.setManager(true);

        //Creating a sample project.
        Project project = new Project(
                1L,
                "Sample Project",
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                LocalDate.now().plusMonths(5),
                "Description",
                ProjectStatus.STARTED,
                100000.0,
                ProjectRisk.LOW,
                manager
        );

        when(projectService.createProject(any())).thenReturn(project);

        // When
        ResponseEntity<Project> response = projectController.createProject(project);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(project, response.getBody());
    }

    @Test
    public void testUpdateProject() {
        // Creating a sample manager (Person)
        Person manager = new Person();
        manager.setId(1L);
        manager.setName("John Doe");
        manager.setBirthDate(LocalDate.of(1980, 1, 15));
        manager.setCpf("12345678901");
        manager.setEmployee(true);
        manager.setManager(true);

        //Creating a sample project.
        Project project = new Project(
                1L,
                "Sample Project",
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                LocalDate.now().plusMonths(5),
                "Description",
                ProjectStatus.STARTED,
                100000.0,
                ProjectRisk.LOW,
                manager
        );

        //Creating a sample updated project.
        Project updatedProject = new Project(
                1L,
                "Update Project",
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                LocalDate.now().plusMonths(5),
                "Description",
                ProjectStatus.IN_PROGRESS,
                100000.0,
                ProjectRisk.HIGH,
                manager
        );

        // Mock service behavior
        when(projectService.updateProject(1L, updatedProject)).thenReturn(updatedProject);

        // Act: Call the controller method
        ResponseEntity<Project> responseEntity = projectController.updateProject(1L, updatedProject);

        // Assert: Check the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedProject, responseEntity.getBody());

        // Verify that the service method was called
        verify(projectService, times(1)).updateProject(1L, updatedProject);
    }

    @Test
    public void testDeleteProject() {
        // Creating a sample manager (Person)
        Person manager = new Person();
        manager.setId(1L);
        manager.setName("John Doe");
        manager.setBirthDate(LocalDate.of(1980, 1, 15));
        manager.setCpf("12345678901");
        manager.setEmployee(true);
        manager.setManager(true);

        //Creating a sample project.
        Project project = new Project(
                1L,
                "Sample Project",
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                LocalDate.now().plusMonths(5),
                "Description",
                ProjectStatus.STARTED,
                100000.0,
                ProjectRisk.LOW,
                manager
        );

        // Act: Call the controller method
        ResponseEntity<Void> responseEntity = projectController.deleteProject(1L);

        // Assert: Check the response
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verify that the service method was called
        verify(projectService, times(1)).deleteProject(1L);
    }

    @Test
    public void testGetProjectById() {
        // Creating a sample manager (Person)
        Person manager = new Person();
        manager.setId(1L);
        manager.setName("John Doe");
        manager.setBirthDate(LocalDate.of(1980, 1, 15));
        manager.setCpf("12345678901");
        manager.setEmployee(true);
        manager.setManager(true);

        //Creating a sample project.
        Project project = new Project(
                1L,
                "Sample Project",
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                LocalDate.now().plusMonths(5),
                "Description",
                ProjectStatus.STARTED,
                100000.0,
                ProjectRisk.LOW,
                manager
        );
        // Mock service behavior
        when(projectService.getProjectById(1L)).thenReturn(project);

        // Act: Call the controller method
        ResponseEntity<Project> responseEntity = projectController.getProjectById(1L);

        // Assert: Check the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(project, responseEntity.getBody());

        // Verify that the service method was called
        verify(projectService, times(1)).getProjectById(1L);
    }

    @Test
    public void testGetProjectById_NotFound() {
        // Arrange: Mock data
        Long projectId = 1L;

        // Mock service behavior for a non-existing project
        when(projectService.getProjectById(projectId)).thenReturn(null);

        // Act & Assert: Attempt to get a non-existing project, expect an exception
        assertThrows(IllegalArgumentException.class, () -> projectController.getProjectById(projectId));
    }

}