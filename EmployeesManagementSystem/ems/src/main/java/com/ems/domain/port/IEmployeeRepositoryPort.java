package com.ems.domain.port;

import com.ems.domain.model.Employee;

import java.util.List;

//This repository port communicates with the database (Secondary port)
public interface IEmployeeRepositoryPort {
    void saveEmployee(Employee employee);
    Employee getEmployee(Long id);
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
