package com.ems.Employee.infrastructure.output.jpa.repository;

import com.ems.Employee.infrastructure.output.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
