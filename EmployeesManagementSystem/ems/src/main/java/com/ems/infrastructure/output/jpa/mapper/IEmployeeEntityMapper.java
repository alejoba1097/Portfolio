package com.ems.infrastructure.output.jpa.mapper;

import com.ems.domain.model.Employee;
import com.ems.infrastructure.output.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeEntityMapper {
    EmployeeEntity toEntity(Employee employee);
    Employee toEmployee(EmployeeEntity employeeEntity);

    List<Employee> toEmployeeList(List<EmployeeEntity> employeeEntityList);
    List<EmployeeEntity> toEmployeeEntityList(List<Employee> employeeList);
}