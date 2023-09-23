package com.ems.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmployeeId;
    private String FirstName;
    private String LastName;
    private String Email;
    private LocalDate HireDate;
    private String PhoneNumber;
    private String CurrentStatus;
    private Long ManagerID;
    private Long DepartmentID;
    private Long RoleId;
}
