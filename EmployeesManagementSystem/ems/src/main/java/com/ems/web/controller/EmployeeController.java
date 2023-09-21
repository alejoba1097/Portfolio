package com.ems.web.controller;

import com.ems.domain.DomainEmployee;
import com.ems.domain.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<DomainEmployee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/employee/{id}")
    public Optional<DomainEmployee> getEmployee(@PathVariable("id") employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/role/{id}")
    public Optional<List<DomainEmployee>> getByRole(@PathVariable("id") int roleId){
        return employeeService.getByRole(roleId);
    }

    @PostMapping("/save")
    public DomainEmployee save(@RequestBody DomainEmployee domainEmployee){
        return employeeService.save(domainEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int employeeId){
        return getEmployee(employeeId).map(employee -> {
            employeeService.delete(employeeId);
            return true;
        }).orElse(false);
    }
}
