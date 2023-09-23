package com.ems.application.mapper;

import com.ems.application.dto.model.DepartmentDto;
import com.ems.application.dto.model.EmployeeDto;
import com.ems.application.dto.model.RoleDto;
import com.ems.application.dto.response.EmployeeResponse;
import com.ems.domain.model.Department;
import com.ems.domain.model.Employee;
import com.ems.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import static java.util.stream.Collectors.toList;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IDepartmentDtoMapper.class, IRoleDtoMapper.class})

public interface IEmployeeResponseMapper {
    // You never map a Model. You map the ModelDto to a Response
    EmployeeResponse toEmployeeResponse(EmployeeDto employeeDto);

    default List<EmployeeResponse> toResponseList(List<EmployeeDto> employeeDtoList){
        return employeeDtoList.stream()
                .map(employeeDto -> {
                    EmployeeResponse employeeResponse = new EmployeeResponse();
                    employeeResponse.setId(employeeDto.getId());
                    employeeResponse.setName(employeeDto.getName());
                    employeeResponse.setLastName(employeeDto.getLastName());
                    // employeeResponse.setDepartment(employeeResponse.getDepartment());
                    // employeeResponse.setRole(employeeResponse.getRole());
                    return employeeResponse;
                }).collect(toList());
    }
}
