package com.ems.infrastructure.input.rest;

import com.ems.application.dto.model.EmployeeDto;
import com.ems.application.dto.request.EmployeeRequest;
import com.ems.application.dto.response.EmployeeResponse;
import com.ems.application.handler.IEmployeeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/")
@RequiredArgsConstructor
public class EmployeeRestController {
    private final IEmployeeHandler employeeHandler;

    @PostMapping
    public ResponseEntity<Void> saveEmployee(@RequestBody EmployeeRequest employeeRequest){
        employeeHandler.saveEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id){
        return ResponseEntity.ok(employeeHandler.getEmployee(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return ResponseEntity.ok(employeeHandler.getAllEmployees());
    }

    @PutMapping
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeRequest employeeRequest){
        employeeHandler.updateEmployee(employeeRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeHandler.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
