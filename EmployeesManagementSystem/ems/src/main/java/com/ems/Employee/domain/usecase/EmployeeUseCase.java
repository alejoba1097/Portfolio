package com.ems.Employee.domain.usecase;

import com.ems.Employee.domain.api.IEmployeeRepositoryPort;
import com.ems.Employee.domain.api.IEmployeeServicePort;
import com.ems.Employee.domain.model.Employee;

import java.util.List;

public class EmployeeUseCase implements IEmployeeServicePort {
    private final IEmployeeRepositoryPort employeeRepositoryPort;

    public EmployeeUseCase(IEmployeeRepositoryPort employeeRepositoryPort) {
        this.employeeRepositoryPort = employeeRepositoryPort;
    }

    @Override
    public void save(Employee employee) {
        employeeRepositoryPort.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepositoryPort.getAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepositoryPort.getEmployee(id);
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
