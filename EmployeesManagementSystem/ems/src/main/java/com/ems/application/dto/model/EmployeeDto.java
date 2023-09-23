package com.ems.application.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private Long id;
    private String name;
    private String lastName;
    // private EmployeeDto manager;
    // private RoleDto role;
    // private DepartmentDto department;
}
