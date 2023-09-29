package com.ems.Employee.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}