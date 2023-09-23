package com.ems.application.mapper;

import com.ems.application.dto.request.EmployeeRequest;
import com.ems.domain.model.Department;
import com.ems.domain.model.Employee;
import com.ems.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeRequestMapper {
    // You never map to request. You map a request to the Domain model, in this case, Employee
    Employee toEmployee(EmployeeRequest employeeRequest);

    // @Mapping(source = "employeeRequest.Role.role", target = "role")
    // Role toRole(EmployeeRequest employeeRequest);

    // @Mapping(source = "employeeRequest.Department.department", target = "department")
    // Department toDepartment(EmployeeRequest employeeRequest);
}
