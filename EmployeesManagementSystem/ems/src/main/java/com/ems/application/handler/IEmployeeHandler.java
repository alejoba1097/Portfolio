package com.ems.application.handler;

import com.ems.application.dto.request.EmployeeRequest;
import com.ems.application.dto.response.EmployeeResponse;

import java.util.List;

public interface IEmployeeHandler {
    void saveEmployee(EmployeeRequest employeeRequest);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployee(Long id);
    void updateEmployee(EmployeeRequest employeeRequest);
    void deleteEmployee(Long id);
}
