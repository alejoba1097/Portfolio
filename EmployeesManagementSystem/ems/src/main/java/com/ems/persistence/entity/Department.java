package com.ems.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private Integer departmentID;

    @Column(name = "DepartmentName")
    private String departmentName;

    @Column(name = "DepartmentDescription")
    private String departmentDescription;

    @Column(name = "DeptManagerID")
    private String deptManagerID;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "DeptManagerID", insertable = false, updatable = false)
    private Employee deptManager;

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public String getDeptManagerID() {
        return deptManagerID;
    }

    public void setDeptManagerID(String deptManagerID) {
        this.deptManagerID = deptManagerID;
    }
}
