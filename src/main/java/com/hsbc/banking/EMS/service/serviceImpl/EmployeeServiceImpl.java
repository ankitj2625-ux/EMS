package com.hsbc.banking.EMS.service.serviceImpl;

import com.hsbc.banking.EMS.entity.Employee;
import com.hsbc.banking.EMS.globalException.exception.EmployeeNotFoundException;
import com.hsbc.banking.EMS.mapper.EmployeeMapper;
import com.hsbc.banking.EMS.model.request.EmployeeRequest;
import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import com.hsbc.banking.EMS.repository.EmployeeRepository;
import com.hsbc.banking.EMS.util.EmployeeUtils;
import com.hsbc.banking.EMS.util.enums.Operator;
import com.hsbc.banking.EMS.validator.EmployeeValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeServiceImpl {

    private final EmployeeUtils employeeUtils;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeValidator employeeValidator;


    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        employeeValidator.validateEmployeeRequest(employeeRequest);
        Employee employee = employeeMapper.mapToEmployee(employeeRequest, null);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToEmployeeResponse(savedEmployee);
    }

    //TODO: add validation for employeeId and add cacheble
    public EmployeeResponse getEmployeeById(Long employeeId) {
        employeeValidator.mandatoryParameterCheck(employeeId, "employee Id");
        employeeValidator.parameterTypeCheck(employeeId, "employee Id", Long.class);
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        return employeeMapper.mapToEmployeeResponse(employee);
    }

    //TODO: Introduce cachable
    public List<EmployeeResponse> getEmployees() {
        return employeeMapper.mapToEmployeeResponseList(employeeRepository.findAll());
    }

    public EmployeeResponse updateEmployeeById(EmployeeRequest employeeRequest, Long employeeId) {
        employeeValidator.validateEmployeeRequest(employeeRequest);
        Employee employee = employeeMapper.mapToEmployee(employeeRequest, employeeId);
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToEmployeeResponse(updatedEmployee);


//        try {
//            if (employeeId == null) {
//                throw new RuntimeException("Employee ID cannot be null");
//            }
//            if (employeeRequest.getEmployeeAge() < 0) {
//                throw new RuntimeException("salary must be non-negative");
//            }
//            existingEmployee.setEmployeeName(employeeRequest.getEmployeeName());
//            existingEmployee.setEmployeeAge(employeeRequest.getEmployeeAge());
//            existingEmployee.setGender(employeeRequest.getGender());
//            existingEmployee.setSalary(employeeRequest.getSalary());

//            EmployeeResponse employeeResponse = new EmployeeResponse();
//            employeeResponse.setEmployeeId(employee.getEmployeeId());
//            employeeResponse.setEmployeeName(employee.getEmployeeName());
//            employeeResponse.setEmployeeAge(employee.getEmployeeAge());
//            employeeResponse.setGender(employee.getGender());
//            employeeResponse.setSalary(employee.getSalary());
//            return employeeResponse;
    }

    public List<EmployeeResponse> getEmployeeByGender(String gender) {

        EmployeeValidator.validateGender(gender);
        List<Employee> employeeList = employeeRepository.findEmployeeByGender(gender);
        return employeeList.stream()
                .filter(emp -> emp.getGender().equalsIgnoreCase(gender))
                .map(employeeMapper::mapToEmployeeResponse)
                .toList();


        /* try {
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
        }*/
    }

    public List<EmployeeResponse> getEmployeeByAge(Integer age) {
        try {
            List<EmployeeResponse> employeeList = employeeRepository.findByEmployeeAge(age);
            if (employeeList.isEmpty()) {
                throw new EmployeeNotFoundException("No employees found with age: " + age);
            }

            return employeeList.stream()
                    .map(emp -> EmployeeResponse.builder()
                            .employeeId(emp.getEmployeeId())
                            .employeeName(emp.getEmployeeName())
                            .employeeAge(emp.getEmployeeAge())
                            .gender(emp.getGender())
                            .salary(emp.getSalary())
                            .build()).toList();
        } catch (Exception ex) {
            throw new EmployeeNotFoundException("Error retrieving employee with age " + age + ": " + ex.getMessage());
        }
    }

    public EmployeeResponse getSalaryById(Long employeeId) {
        return null;
    }

    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public EmployeeResponse updateEmployeeSalaryById(Long employeeId, Double salary) {
        return null;
    }

    public EmployeeResponse updateEmployeeAgeById(Long employeeId, Double salary) {
        return null;
    }


    public List<EmployeeResponse> getEmployee(EmployeeRequest employeeRequest) {
        //TODO: getemployee by multiple filters
        return null;
    }

    public List<EmployeeResponse> getEmployeesBySalary(Double salary, Operator operator) {

        List<EmployeeResponse> employees = getEmployees();
        List<EmployeeResponse> filteredEmployee = employees.stream().filter(emp -> {
            switch (operator) {
                case GREATER:
                    return emp.getSalary() > salary;
                case LESS:
                    return emp.getSalary() < salary;
                case EQUALS:
                    return emp.getSalary().equals(salary);
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }).toList();
        return filteredEmployee;


    }
}





















