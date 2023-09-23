package com.ems.application.dto.request;

import com.ems.domain.model.Department;
import com.ems.domain.model.Employee;
import com.ems.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//This makes a request to the API based on an object from the Domain
public class EmployeeRequest {
    private Long id;
    private String name;
    private String lastName;
    // private Employee manager;
    // private Role role;
    // private Department department;
}
