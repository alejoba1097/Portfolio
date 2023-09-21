package com.ems.persistence.mapper;

import com.ems.domain.DomainEmployee;
import com.ems.persistence.entity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface EmployeeMapper {

    @Mappings({
            @Mapping(source = "employeeId", target = "employeeId"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "departmentId", target = "deptId"),
            @Mapping(source = "roleId", target = "roleId"),
            @Mapping(source = "currentStatus", target = "status")
            // @Mapping(source = "role", target = "roleName")
    })
    DomainEmployee toDomainEmployee(Employee employee);
    List<DomainEmployee> toDomainEmployees(List<Employee> employees);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "lastName",  ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "hireDate", ignore = true),
            @Mapping(target = "phoneNumber", ignore = true),
            @Mapping(target = "managerId", ignore = true)
            // @Mapping(target = "department", ignore = true),
            // @Mapping(target = "managers", ignore = true),
            // @Mapping(target = "projects", ignore = true),
            // @Mapping(target = "employees", ignore = true)
    })
    Employee toEmployee(DomainEmployee domainEmployee);
    List<Employee> toEmployees(List<DomainEmployee> domainEmployees);
}
