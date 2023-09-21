package com.ems.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name  = "Projects")
public class Project {
    @Id
    @Column(name = "ProjectID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @Column(name = "ProjectDescription")
    private String projectDescription;

    @Column(name = "LocationID")
    private String locationId;

    @ManyToOne
    @JoinColumn(name = "LocationID", insertable = false, updatable = false)
    private Location location;

    @OneToMany(mappedBy = "employee")
    private List<Employee> employees;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
