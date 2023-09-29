package com.ems.Employee.domain.api;

import com.ems.Employee.domain.model.Employee;

import java.util.List;

public interface IEmployeeRepositoryPort {
    void save(Employee employee);
    List<Employee> getAll();
    Employee getEmployee(Long id);
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
}
