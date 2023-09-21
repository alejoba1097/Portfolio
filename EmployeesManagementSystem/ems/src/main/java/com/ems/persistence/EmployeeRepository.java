package com.ems.persistence;

import com.ems.domain.DomainEmployee;
import com.ems.domain.repository.DomainEmployeeRepository;
import com.ems.persistence.crud.EmployeeCrudRepository;
import com.ems.persistence.entity.Employee;
import com.ems.persistence.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository implements DomainEmployeeRepository {
    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Autowired
    private EmployeeMapper mapper;

    @Override
    public List<DomainEmployee> getAll(){
        List<Employee> employees = (List<Employee>) employeeCrudRepository.findAll();
        return mapper.toDomainEmployees(employees);
    }

    @Override
    public Optional<List<DomainEmployee>> getByRole(int roleId){
        List<Employee> employees = employeeCrudRepository.findByRoleIdOrderByFirstNameAsc(roleId);
        return Optional.of(mapper.toDomainEmployees(employees));

    }

    @Override
    public Optional<List<DomainEmployee>> getNewHires(LocalDate date, String status){
         Optional<List<Employee>> employees = employeeCrudRepository.findByHireDateLessThanAndCurrentStatus(date, status);
         return employees.map(e -> mapper.toDomainEmployees(e));
    }

    @Override
    public Optional<DomainEmployee> getEmployee(int employeeId){
        Optional<Employee> employee = employeeCrudRepository.findById(employeeId);
        return employee.map(e -> mapper.toDomainEmployee(e));
    }

    @Override
    public DomainEmployee save(DomainEmployee domainEmployee) {
        Employee employee = mapper.toEmployee(domainEmployee);
        return mapper.toDomainEmployee(employeeCrudRepository.save(employee));
    }

    @Override
    public void delete(int employeeId) {
        employeeCrudRepository.deleteById(employeeId);
    }


}
