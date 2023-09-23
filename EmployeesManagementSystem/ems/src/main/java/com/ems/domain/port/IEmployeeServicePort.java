package com.ems.domain.port;

import com.ems.domain.model.Employee;

import java.util.List;

//This service port communicates directly with the API (Primary port)
public interface IEmployeeServicePort {
    void saveEmployee(Employee employee);
    Employee getEmployee(Long id);
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
