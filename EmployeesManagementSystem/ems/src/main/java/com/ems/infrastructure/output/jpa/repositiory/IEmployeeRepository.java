package com.ems.infrastructure.output.jpa.repositiory;

import com.ems.infrastructure.output.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEmployeeRepository  extends JpaRepository<EmployeeEntity, Long> {

}
