package com.ems.persistence.entity;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;

@Entity
@Table(name = "ProjectsEmployees")
public class ProjectsEmployee {
    @EmbeddedId
    private ProjectsEmployeePK id;

    @ManyToOne
    @JoinColumn(name = "ProjectID", insertable = false, updatable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "EmployeeID", insertable = false, updatable = false)
    private Employee employee;

    public ProjectsEmployeePK getId() {
        return id;
    }

    public void setId(ProjectsEmployeePK id) {
        this.id = id;
    }
}
