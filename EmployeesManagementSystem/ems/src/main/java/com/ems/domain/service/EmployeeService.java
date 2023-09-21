package com.ems.domain.service;

import com.ems.domain.DomainEmployee;
import com.ems.domain.repository.DomainEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private DomainEmployeeRepository domainEmployeeRepository;

    public List<DomainEmployee> getAll(){
        return domainEmployeeRepository.getAll();
    }

    public Optional<DomainEmployee> getEmployee(int employeeId){
        return domainEmployeeRepository.getEmployee(employeeId);
    }

    public Optional<List<DomainEmployee>> getByRole(int roleId){
        return domainEmployeeRepository.getByRole(roleId);
    }

    public DomainEmployee save(DomainEmployee domainEmployee){
        return domainEmployeeRepository.save(domainEmployee);
    }

    public boolean delete(int employeeId){
        return getEmployee(employeeId).map(employee -> {
            domainEmployeeRepository.delete(employeeId);
            return true;
        }).orElse(false);
    }
}

