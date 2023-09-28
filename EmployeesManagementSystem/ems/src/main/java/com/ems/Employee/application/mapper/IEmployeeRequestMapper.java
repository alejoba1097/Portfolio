package com.ems.Employee.application.mapper;

import com.ems.Employee.application.dto.EmployeeRequestDto;
import com.ems.Employee.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IEmployeeRequestMapper {
    Employee toEmployee(EmployeeRequestDto employeeRequestDto);

    List<Employee> toEmployeeList(List<EmployeeRequestDto> employeeRequestDtoList);
}
