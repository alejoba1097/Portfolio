package com.ems.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.ems.domain.DomainEmployee;

public interface DomainEmployeeRepository {
    List<DomainEmployee> getAll();
    Optional<List<DomainEmployee>> getByRole(int roleId);
    Optional<List<DomainEmployee>> getNewHires(LocalDate date, String status);
    Optional<DomainEmployee> getEmployee(int employeeId);
    DomainEmployee save(DomainEmployee domainEmployee);
    void delete(int employeeId);
}
