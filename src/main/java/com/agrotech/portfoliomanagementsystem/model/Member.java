package com.agrotech.portfoliomanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MemberId.class)
public class Member {

    @Id
    @Column(name = "project_id")
    private Long projectId;

    @Id
    @Column(name = "person_id")
    private Long personId;

    @ManyToOne
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;
}