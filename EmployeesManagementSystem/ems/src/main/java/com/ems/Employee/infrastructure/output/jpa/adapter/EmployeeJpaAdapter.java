package com.ems.Employee.infrastructure.output.jpa.adapter;

import com.ems.Employee.domain.api.IEmployeeRepositoryPort;
import com.ems.Employee.domain.model.Employee;
import com.ems.Employee.infrastructure.output.jpa.mapper.IEmployeeEntityMapper;
import com.ems.Employee.infrastructure.output.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeJpaAdapter implements IEmployeeRepositoryPort {
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employeeEntityMapper.toEmployeeEntity(employee));
    }

    @Override
    public List<Employee> getAll() {
        return employeeEntityMapper.toEmployeeList(employeeRepository.findAll());
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeEntityMapper.toEmployee(employeeRepository.findById(id));
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employeeEntityMapper.toEmployeeEntity(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
