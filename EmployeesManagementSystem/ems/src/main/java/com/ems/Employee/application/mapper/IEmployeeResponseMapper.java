package com.ems.Employee.application.mapper;

import com.ems.Employee.application.dto.EmployeeResponseDto;
import com.ems.Employee.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IEmployeeResponseMapper {
    IEmployeeResponseMapper INSTANCE = Mappers.getMapper(IEmployeeResponseMapper.class);

    // @Mapping(source = "id", target = "id")
    EmployeeResponseDto toEmployeeResponseDto(Employee employee);

    List<EmployeeResponseDto> toEmployeeResponseDtoList(List<Employee> employeeList);
}
