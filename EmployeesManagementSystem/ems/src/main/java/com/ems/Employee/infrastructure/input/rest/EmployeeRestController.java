package com.ems.Employee.infrastructure.input.rest;

import com.ems.Employee.application.dto.EmployeeRequestDto;
import com.ems.Employee.application.dto.EmployeeResponseDto;
import com.ems.Employee.application.mapper.IEmployeeRequestMapper;
import com.ems.Employee.application.mapper.IEmployeeResponseMapper;
import com.ems.Employee.domain.api.IEmployeeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/rest")
@RequiredArgsConstructor
public class EmployeeRestController {
    private final IEmployeeServicePort employeeServicePort;
    private final IEmployeeResponseMapper employeeResponseMapper;
    private final IEmployeeRequestMapper employeeRequestMapper;

    @PostMapping("/add")
    public ResponseEntity saveEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        employeeServicePort.save(employeeRequestMapper.toEmployee(employeeRequestDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable Long id){
        return new ResponseEntity<>(
                employeeResponseMapper.toEmployeeResponseDto(employeeServicePort.getEmployee(id)),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees(){
        return new ResponseEntity<>(
                employeeResponseMapper.toEmployeeResponseDtoList(employeeServicePort.getAll()),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        employeeServicePort.updateEmployee(employeeRequestMapper.toEmployee(employeeRequestDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeServicePort.deleteEmployee(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
