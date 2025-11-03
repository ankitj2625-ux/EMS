package com.hsbc.banking.EMS.service.serviceImpl;

import com.hsbc.banking.EMS.entity.Employee;
import com.hsbc.banking.EMS.globalException.exception.EmployeeCreationException;
import com.hsbc.banking.EMS.globalException.exception.EmployeeNotFoundException;
import com.hsbc.banking.EMS.model.request.EmployeeRequest;
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
                throw new EmployeeCreationException("salary must be non-negative");
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
                    .salary(savedEmployee.getSalary())
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
                throw new RuntimeException("Employee salary is null for id: " + employeeId);
            }

            return EmployeeResponse.builder()
                    .employeeId(employee.getEmployeeId())
                    .employeeName(employee.getEmployeeName())
                    .employeeAge(employee.getEmployeeAge())
                    .salary(employee.getSalary())
                    .gender(employee.getGender())
                    .build();
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Error retrieving employee with id " + employeeId + ": " + ex.getMessage());
        }
    }

    public List<EmployeeResponse> getAllEmployee() {
        return employeeRepository.findAll().stream()
                .map(emp -> {
                    EmployeeResponse employeeResponse = new EmployeeResponse();
                    employeeResponse.setEmployeeId(emp.getEmployeeId());
                    employeeResponse.setEmployeeName(emp.getEmployeeName());
                    employeeResponse.setEmployeeAge(emp.getEmployeeAge());
                    employeeResponse.setGender(emp.getGender());
                    employeeResponse.setSalary(emp.getSalary());
                    return employeeResponse;
                }).collect(Collectors.toList());
    }

    public EmployeeResponse updateEmployeeById(EmployeeRequest employeeRequest, Long employeeId) {

        try {
            if (employeeId == null) {
                throw new RuntimeException("Employee ID cannot be null");
            }
            if (employeeRequest.getEmployeeAge() < 0) {
                throw new RuntimeException("salary must be non-negative");
            }

            Employee existingEmployee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
            existingEmployee.setEmployeeName(employeeRequest.getEmployeeName());
            existingEmployee.setEmployeeAge(employeeRequest.getEmployeeAge());
            existingEmployee.setGender(employeeRequest.getGender());
            existingEmployee.setSalary(employeeRequest.getSalary());
            Employee employee = employeeRepository.save(existingEmployee);

            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setEmployeeId(employee.getEmployeeId());
            employeeResponse.setEmployeeName(employee.getEmployeeName());
            employeeResponse.setEmployeeAge(employee.getEmployeeAge());
            employeeResponse.setGender(employee.getGender());
            employeeResponse.setSalary(employee.getSalary());
            return employeeResponse;

        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Error updating employee with id " + employeeId + ": " + ex.getMessage());
        }
    }

    public List<EmployeeResponse> getEmployeeByGender(String gender) {
        try {
            List<Employee> employee = employeeRepository.findEmployeeByGender(gender);
            if (employee.isEmpty()) {
                throw new EmployeeNotFoundException("No employees found with gender: " + gender);
            }
            return employee.stream()
                    .map(emp -> {
                        return EmployeeResponse.builder()
                                .employeeId(emp.getEmployeeId())
                                .employeeName(emp.getEmployeeName())
                                .employeeAge(emp.getEmployeeAge())
                                .gender(emp.getGender())
                                .salary(emp.getSalary())  // ✅ lowercase
                                .build();
                    })
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Error retrieving employee with gender " + gender + ": " + ex.getMessage());
        }
    }

}
