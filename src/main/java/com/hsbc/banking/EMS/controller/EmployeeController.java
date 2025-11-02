package com.hsbc.banking.EMS.controller;

import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import com.hsbc.banking.EMS.service.EmployeeService;
import com.hsbc.banking.EMS.service.serviceImpl.EmployeeServiceImpl;
import com.hsbc.banking.EMS.util.EmployeeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeUtils employeeUtils;
    private final EmployeeServiceImpl employeeServiceimpl;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        List<EmployeeResponse> responses = employeeUtils.generateEmployees();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("API is running", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeServiceimpl.getEmployeeById(id);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

}
