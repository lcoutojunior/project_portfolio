package com.agrotech.portfoliomanagementsystem.model;

import com.agrotech.portfoliomanagementsystem.enums.ProjectStatus;
import com.agrotech.portfoliomanagementsystem.enums.ProjectRisk;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "begin_date")
    private LocalDate beginDate;

    @Column(name = "expected_end_date")
    private LocalDate expectedEndDate;

    @Column(name = "actual_end_date")
    private LocalDate actualEndDate;

    @Column(name = "description", length = 5000)
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Column(name = "total_budget")
    private Double totalBudget;

    @Column(name = "risk")
    @Enumerated(EnumType.STRING)
    private ProjectRisk risk;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Person manager;

    // Additional business rule validations
    @PreUpdate
    @PrePersist
    void validateProjectStatus() {
        if (status == ProjectStatus.STARTED || status == ProjectStatus.IN_PROGRESS || status == ProjectStatus.CLOSED) {
            // Once a project is started, in progress, or closed, it cannot be deleted
            if (id != null) {
                throw new IllegalStateException("Cannot delete a project with status started, in progress, or closed.");
            }
        }
    }

}