package com.hsbc.banking.EMS.controller;

import com.hsbc.banking.EMS.constants.AppConstant;
import com.hsbc.banking.EMS.model.request.EmployeeRequest;
import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import com.hsbc.banking.EMS.service.serviceImpl.EmployeeServiceImpl;
import com.hsbc.banking.EMS.util.enums.Operator;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    Logger logger = Logger.getLogger(EmployeeController.class.getName());
    private final EmployeeServiceImpl employeeService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("API is running", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        List<EmployeeResponse> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse response = employeeService.createEmployee(employeeRequest);
        log.info(AppConstant.EMPLOYEE_CREATED);
        return new ResponseEntity<>(response, null, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(employeeId);
        MultiValueMap<String, String> headers = MultiValueMap.fromSingleValue(Map.of("200", AppConstant.EMPLOYEE_FETCHED));
        return new ResponseEntity<>(employeeResponse, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeeById(@RequestBody EmployeeRequest employeeRequest,
                                                               @PathVariable("id") Long employeeId) {
        EmployeeResponse employeeResponse = employeeService.updateEmployeeById(employeeRequest, employeeId);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeResponse response = employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}/salary")
    public ResponseEntity<EmployeeResponse> updateEmployeeSalaryById(@PathVariable("id") Long employeeId,
                                                                     @RequestParam("salary") Double salary) {
        EmployeeResponse employeeResponse = employeeService.updateEmployeeSalaryById(employeeId, salary);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @PatchMapping("/{id}/age")
    public ResponseEntity<EmployeeResponse> updateEmployeeAgeById(@PathVariable("id") Long employeeId,
                                                                  @RequestParam("age") Double salary) {
        EmployeeResponse employeeResponse = employeeService.updateEmployeeAgeById(employeeId, salary);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/gender")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByGender(@RequestParam("gender") String gender) {
        List<EmployeeResponse> employeeResponse = employeeService.getEmployeeByGender(gender);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByAge(@RequestParam("age") Integer age) {
        List<EmployeeResponse> employeeList = employeeService.getEmployeeByAge(age);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/salary")
    public ResponseEntity<EmployeeResponse> getSalaryById(@RequestParam Long employeeId) {
        EmployeeResponse employeeResponse = employeeService.getSalaryById(employeeId);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeResponse>> getEmployee(@RequestParam EmployeeRequest employeeRequest) {
        List<EmployeeResponse> employeeResponse = employeeService.getEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/filterdEmployees")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesBySalary(@Nullable @RequestParam Double salary, @RequestParam Operator operator) {
        List<EmployeeResponse> employeeResponse = employeeService.getEmployeesBySalary(salary, operator);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }
    //localhost:8080/employees/filterdEmployees?salary=50000&operator=GREATER

    //TODO: get List of By Department ID;

}
