package com.ems.persistence.crud;

import com.ems.persistence.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {

    //Native query can be used as well
    // @Query(value = "SELECT * FROM Employees WHERE EmployeeId = ?", nativeQuery = true)
    List<Employee> findByRoleIdOrderByFirstNameAsc(int roleId);

    Optional<List<Employee>> findByHireDateLessThanAndCurrentStatus(LocalDate hireDate, String currentStatus);
}
