package com.ems.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @Column(name = "EmployeeID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "HireDate")
    private LocalDate hireDate;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "CurrentStatus")
    private String currentStatus;

    @Column(name = "ManagerID")
    private Integer managerId;

    @Column(name = "DepartmentID")
    private Integer departmentId;

    @Column(name = "RoleID")
    private Integer roleId;

    @ManyToOne
    @JoinColumn(name = "DepartmentID", insertable = false, updatable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "RoleID", insertable = false, updatable = false)
    private Role role;

    @OneToMany(mappedBy = "deptManager")
    private List<Employee> managers;

    @OneToMany(mappedBy = "project")
    private List<ProjectsEmployee> projects;

    @OneToMany
    private List<Employee> employees;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        if (currentStatus == "Active" || currentStatus == "Not Active") {
            this.currentStatus = currentStatus;
        } else {
            System.out.println("Error: Status can only be Active or Not Active");
        }

    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
