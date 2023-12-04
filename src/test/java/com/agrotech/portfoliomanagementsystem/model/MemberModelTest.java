package com.agrotech.portfoliomanagementsystem.model;

import com.agrotech.portfoliomanagementsystem.enums.ProjectRisk;
import com.agrotech.portfoliomanagementsystem.enums.ProjectStatus;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberModelTest {

    @Test
    void testMemberModelCreation() {

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
        project.setId(2L);
        project.setName("Sample Project");
        project.setBeginDate(LocalDate.of(1990, 5, 15));
        project.setExpectedEndDate(LocalDate.of(1990, 6, 16));
        project.setActualEndDate(LocalDate.of(1990, 7, 17));
        project.setDescription("A Sample Description");
        project.setStatus(ProjectStatus.STARTED);
        project.setTotalBudget(10000.10);
        project.setRisk(ProjectRisk.LOW);
        project.setManager(manager);

        // When
        Member member = new Member(1L, 2L, project, manager);

        // Then
        assertEquals(1L, member.getProjectId());
        assertEquals(2L, member.getPersonId());
        assertEquals(project, member.getProject());
        assertEquals(manager, member.getPerson());
    }
}

