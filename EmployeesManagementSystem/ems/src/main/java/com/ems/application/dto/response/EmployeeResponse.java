package com.ems.application.dto.response;

import com.ems.application.dto.model.DepartmentDto;
import com.ems.application.dto.model.EmployeeDto;
import com.ems.application.dto.model.RoleDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//This response will receive an object from the outside
public class EmployeeResponse {
    private Long id;
    private String name;
    private String lastName;
    // private EmployeeDto manager;
    // private RoleDto role;
    // private DepartmentDto department;
}
