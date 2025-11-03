package com.hsbc.banking.EMS.service.serviceImpl;

import com.hsbc.banking.EMS.entity.Employee;
import com.hsbc.banking.EMS.globalException.exception.EmployeeCreationException;
import com.hsbc.banking.EMS.globalException.exception.EmployeeNotFoundException;
import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import com.hsbc.banking.EMS.repository.EmployeeRepository;
import com.hsbc.banking.EMS.util.EmployeeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl {

    private final EmployeeUtils employeeUtils;
    private final EmployeeRepository employeeRepository;

    public EmployeeResponse creteEmployee(EmployeeResponse employeeRequest) {

        try {
            if (employeeRequest == null) {
                throw new RuntimeException("Employee request cannot be null");
            }
            if (employeeRequest.getSalary() == null || employeeRequest.getSalary() < 0) {
                throw new EmployeeCreationException("Salary must be non-negative");
            }

            Employee employee = new Employee();
            employee.setEmployeeName(employeeRequest.getEmployeeName());
            employee.setEmployeeAge(employeeRequest.getEmployeeAge());
            employee.setGender(employeeRequest.getGender());
            employee.setSalary(employeeRequest.getSalary());
            Employee savedEmployee = employeeRepository.save(employee);

            return EmployeeResponse.builder()
                    .employeeName(savedEmployee.getEmployeeName())
                    .employeeAge(savedEmployee.getEmployeeAge())
                    .gender(savedEmployee.getGender())
                    .Salary(savedEmployee.getSalary())
                    .build();

        } catch (Exception ex) {
            throw new EmployeeCreationException("Failed to create employee: " + ex.getMessage());
        }
    }

    public EmployeeResponse getEmployeeById(Long employeeId) {
        try {
            Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
            if (employee.getEmployeeId() == null) {
                throw new RuntimeException("Employee ID is null for id: " + employeeId);
            }
            if (employee.getSalary() == null) {
                throw new RuntimeException("Employee Salary is null for id: " + employeeId);
            }

            return EmployeeResponse.builder()
                    .employeeId(employee.getEmployeeId())
                    .employeeName(employee.getEmployeeName())
                    .employeeAge(employee.getEmployeeAge())
                    .Salary(employee.getSalary())
                    .gender(employee.getGender())
                    .build();
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Error retrieving employee with id " + employeeId + ": " + ex.getMessage());
        }
    }

    public List<EmployeeResponse> getAllEmployee() {
       return employeeRepository.findAll().stream()
               .map( emp -> {
           EmployeeResponse employeeResponse = new EmployeeResponse();
           employeeResponse.setEmployeeId(emp.getEmployeeId());
           employeeResponse.setEmployeeName(emp.getEmployeeName());
           employeeResponse.setEmployeeAge(emp.getEmployeeAge());
           employeeResponse.setGender(emp.getGender());
           employeeResponse.setSalary(emp.getSalary());
            return employeeResponse;
        }).collect(Collectors.toList());
    }
}
