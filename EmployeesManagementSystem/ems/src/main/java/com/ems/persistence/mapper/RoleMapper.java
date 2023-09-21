package com.ems.persistence.mapper;

import com.ems.domain.DomainRole;
import com.ems.persistence.entity.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mappings({
            @Mapping(source = "roleID", target = "roleId"),
            @Mapping(source = "roleName", target = "roleName")
    })
    DomainRole toRole(Role role);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "responsibilities", ignore = true),
            @Mapping(target = "roleDescription", ignore = true),
            @Mapping(target = "startDate", ignore = true),
            @Mapping(target = "endDate", ignore = true)
            // @Mapping(target = "employees", ignore = true)
    })
    Role toDomainRole(DomainRole role);
}
