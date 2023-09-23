package com.ems.infrastructure.output.jpa.adapter;

import com.ems.domain.model.Employee;
import com.ems.domain.port.IEmployeeRepositoryPort;
import com.ems.infrastructure.exceptions.EmployeeAlreadyExistsException;
import com.ems.infrastructure.exceptions.NoDataFoundException;
import com.ems.infrastructure.exceptions.EmployeeNotFoundException;
import com.ems.infrastructure.output.jpa.entity.EmployeeEntity;
import com.ems.infrastructure.output.jpa.mapper.IEmployeeEntityMapper;
import com.ems.infrastructure.output.jpa.repositiory.IEmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EmployeeJpaAdapter implements IEmployeeRepositoryPort {
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    @Override
    public void saveEmployee(Employee employee) {
        if (employeeRepository.findById(employee.getId()).isPresent()){
            throw new EmployeeAlreadyExistsException();
        }
        employeeRepository.save(employeeEntityMapper.toEntity(employee));
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeEntityMapper.toEmployee(employeeRepository.findById(id)
                .orElseThrow(EmployeeNotFoundException::new));
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        if (employeeEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return employeeEntityMapper.toEmployeeList(employeeEntityList);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employeeEntityMapper.toEntity(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
