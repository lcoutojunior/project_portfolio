package com.agrotech.portfoliomanagementsystem.model;

import com.agrotech.portfoliomanagementsystem.repository.MemberRepository;
import com.agrotech.portfoliomanagementsystem.repository.ProjectRepository;
import com.agrotech.portfoliomanagementsystem.service.ProjectService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.agrotech.portfoliomanagementsystem.enums.ProjectRisk;
import com.agrotech.portfoliomanagementsystem.enums.ProjectStatus;
import java.time.LocalDate;
public class ProjectModelTest {

    @Test
    public void testProjectCreation() {
        // Given
        Person manager = new Person();
        manager.setId(1L);
        manager.setName("John Doe");
        manager.setBirthDate(LocalDate.of(1990, 5, 15));
        manager.setCpf("12345678901");
        manager.setManager(true);
        manager.setEmployee(true);

        // Create a sample project
        Project project = new Project();
        project.setId(1L);
        project.setName("Sample Project");
        project.setBeginDate(LocalDate.now());
        project.setExpectedEndDate(LocalDate.now().plusMonths(6));
        project.setActualEndDate(LocalDate.now().plusMonths(5));
        project.setDescription("This is a sample project description.");
        project.setStatus(ProjectStatus.IN_PROGRESS);
        project.setTotalBudget(100000.0);
        project.setRisk(ProjectRisk.HIGH);
        project.setManager(manager);

        // Validate project creation
        assertNotNull(project.getId());
        assertEquals("Sample Project", project.getName());
        assertEquals(LocalDate.now(), project.getBeginDate());
        assertEquals(LocalDate.now().plusMonths(6), project.getExpectedEndDate());
        assertEquals(LocalDate.now().plusMonths(5), project.getActualEndDate());
        assertEquals("This is a sample project description.", project.getDescription());
        assertEquals(ProjectStatus.IN_PROGRESS, project.getStatus());
        assertEquals(100000.0, project.getTotalBudget());
        assertEquals(ProjectRisk.HIGH, project.getRisk());
        assertEquals(manager, project.getManager());
    }

    @Test
    void deleteProject_StartedStatus_ThrowsException() {
        // Arrange: Create a project with the status "started"
        Project project = new Project();
        project.setId(1L);
        project.setStatus(ProjectStatus.STARTED);

        // Given: Create a person and a member
        Person person = new Person();
        person.setId(2L);

        // Given: Create a MemberId for mocking
        MemberId memberId = new MemberId(1L, 2L);

        Member member = new Member();
        member.setProjectId(1L);
        member.setPersonId(2L);
        project.setStatus(ProjectStatus.STARTED);

        // Mock ProjectRepository
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        when(projectRepository.findById(1L)).thenReturn(java.util.Optional.of(project));

        // Mock MemberRepository
        MemberRepository memberRepository = mock(MemberRepository.class);
        when(memberRepository.findByIdProjectIdAndIdPersonId(memberId.getProjectId(), memberId.getPersonId())).thenReturn(java.util.Optional.of(member));

        // Mock ProjectService
        ProjectService projectService = new ProjectService(projectRepository, memberRepository);

        // Act & Assert: Attempt to delete the project, expect an exception
        assertThrows(IllegalStateException.class, () -> projectService.deleteProject(1L));
    }
}
