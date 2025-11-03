package com.hsbc.banking.EMS.controller;

import com.hsbc.banking.EMS.model.request.EmployeeRequest;
import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import com.hsbc.banking.EMS.service.serviceImpl.EmployeeServiceImpl;
import com.hsbc.banking.EMS.util.EmployeeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeUtils employeeUtils;
    private final EmployeeServiceImpl employeeServiceimpl;

//    @GetMapping("/")
//    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
//        List<EmployeeResponse> responses = employeeUtils.generateEmployees();
//        return new ResponseEntity<>(responses, HttpStatus.OK);
//    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("API is running", HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
//        EmployeeResponse employeeResponse = employeeServiceimpl.getEmployeeById(id);
//        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
//    }

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeResponse employeeRequest) {
        employeeServiceimpl.creteEmployee(employeeRequest);
        return new ResponseEntity<>(employeeRequest, HttpStatus.CREATED);
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeResponse employeeResponse = employeeServiceimpl.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
        List<EmployeeResponse> employeeResponses = employeeServiceimpl.getAllEmployee();
        return new ResponseEntity<>(employeeResponses, HttpStatus.OK);
    }

    @PutMapping("/updateEmployeeById")
    public ResponseEntity<EmployeeResponse> updateEmployeeById(@RequestBody EmployeeRequest employeeRequest,
                                                               @RequestParam("employeeId") Long employeeId) {
        EmployeeResponse employeeResponse = employeeServiceimpl.updateEmployeeById(employeeRequest, employeeId);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/getEmployeeByGender")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByGender(@RequestParam String gender) {
        List<EmployeeResponse> employeeResponse = employeeServiceimpl.getEmployeeByGender(gender);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }
}
