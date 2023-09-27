package com.ems.Employee.infrastructure.output.jpa.mapper;

import com.ems.Employee.domain.model.Employee;
import com.ems.Employee.infrastructure.output.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmployeeEntityMapper {
    Employee toEmployee(Optional<EmployeeEntity> employeeEntity);
    List<Employee> toEmployeeList(List<EmployeeEntity> employeeEntityList);

    EmployeeEntity toEmployeeEntity(Employee employee);
    List<EmployeeEntity> toEmployeeEntityList(List<Employee> employeeList);
}
