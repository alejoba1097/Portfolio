package com.ems.application.handler;

import com.ems.application.dto.request.EmployeeRequest;
import com.ems.application.dto.response.EmployeeResponse;
import com.ems.application.mapper.*;
import com.ems.domain.model.Employee;
import com.ems.domain.port.IEmployeeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //Communicates with the API
@RequiredArgsConstructor
@Transactional
public class EmployeeHandler implements IEmployeeHandler{
    private final IEmployeeServicePort employeeServicePort;
    private final IEmployeeRequestMapper employeeRequestMapper;
    private final IEmployeeResponseMapper employeeResponseMapper;
    private final IEmployeeDtoMapper employeeDtoMapper;

    @Override
    public void saveEmployee(EmployeeRequest employeeRequest) {
        employeeServicePort.saveEmployee(employeeRequestMapper.toEmployee(employeeRequest));
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employeeList = employeeServicePort.getAllEmployees();
        return employeeResponseMapper.toResponseList(employeeDtoMapper.toEmployeeDtoList(employeeList));
    }

    @Override
    public EmployeeResponse getEmployee(Long id) {
        Employee employee = employeeServicePort.getEmployee(id);
        return employeeResponseMapper.toEmployeeResponse(employeeDtoMapper.toEmployeeDto(employee));
    }

    @Override
    public void updateEmployee(EmployeeRequest employeeRequest) {
        Employee oldEmployee = employeeServicePort.getEmployee(employeeRequest.getId());
        Employee newEmployee = employeeRequestMapper.toEmployee(employeeRequest);
        newEmployee.setId(oldEmployee.getId());
        newEmployee.setName(oldEmployee.getName());
        newEmployee.setLastName(oldEmployee.getLastName());
        employeeServicePort.updateEmployee(newEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeServicePort.getEmployee(id);
        employeeServicePort.deleteEmployee(employee.getId());
    }
}
