package com.ems.application.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {
    private String name;
    private EmployeeDto manager;
}
