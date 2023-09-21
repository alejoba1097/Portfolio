package com.ems.persistence.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import java.io.Serializable;

@Embeddable
public class ProjectsEmployeePK implements Serializable {
    @Column(name = "ProjectID")
    private Integer projectId;

    @Column(name = "EmployeeID")
    private Integer employeeId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
