package com.ems.domain.usecase;

import com.ems.domain.model.Employee;
import com.ems.domain.port.IEmployeeRepositoryPort;
import com.ems.domain.port.IEmployeeServicePort;

import java.util.List;

//Employee Use Case: implements "Service" and uses "Repository"
public class EmployeeUseCase implements IEmployeeServicePort {
    private final IEmployeeRepositoryPort employeeRepositoryPort;

    public EmployeeUseCase(IEmployeeRepositoryPort employeeRepositoryPort) {
        this.employeeRepositoryPort = employeeRepositoryPort;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepositoryPort.saveEmployee(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepositoryPort.getEmployee(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepositoryPort.getAllEmployees();
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepositoryPort.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepositoryPort.deleteEmployee(id);
    }
}