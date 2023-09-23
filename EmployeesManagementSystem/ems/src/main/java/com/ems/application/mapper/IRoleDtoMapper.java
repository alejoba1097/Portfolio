package com.ems.application.mapper;

import com.ems.application.dto.model.RoleDto;
import com.ems.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleDtoMapper {
    Role toRole(RoleDto roleDto);
    RoleDto toRoleDto(Role role);
}
