package com.ems.Employee.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private Long id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "HireDate")
    private LocalDate hireDate = LocalDate.of(2022, 1, 01);

    @Column(name = "PhoneNumber")
    private String phoneNumber = "1234";

    @Column(name = "CurrentStatus")
    private String status = "Active";

    @Column(name = "ManagerID")
    private Long managerId = 1000L;

    @Column(name = "DepartmentID")
    private Long departmentId = 1L;

    @Column(name = "RoleId")
    private Long roleId = 1L;

}
