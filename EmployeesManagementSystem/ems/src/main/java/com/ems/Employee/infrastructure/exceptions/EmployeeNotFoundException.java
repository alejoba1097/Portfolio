package com.ems.Employee.infrastructure.exceptions;

import org.springframework.web.server.ResponseStatusException;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(){
        super();
    }
}
