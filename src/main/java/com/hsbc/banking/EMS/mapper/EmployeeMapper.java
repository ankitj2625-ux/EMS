package com.hsbc.banking.EMS.mapper;

import com.hsbc.banking.EMS.entity.Employee;
import com.hsbc.banking.EMS.model.request.EmployeeRequest;
import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EmployeeMapper {

    public EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .employeeAge(employee.getEmployeeAge())
                .gender(employee.getGender())
                .salary(employee.getSalary())
                .build();
    }

    public List<EmployeeResponse> mapToEmployeeResponseList(List<Employee> employees) {
        return employees.stream()
                .map(this::mapToEmployeeResponse)
                .toList();
    }

    public List<EmployeeResponse> mapToEmployeeResponseListName(List<Employee> employees, String name) {
        return employees.stream()
                .map(this::mapToEmployeeResponse)
                .toList();
    }
    public Employee mapToEmployee(EmployeeRequest employeeRequest, Long employeeId) {
        return Employee.builder()
                .employeeId(employeeId)
                .employeeName(employeeRequest.getEmployeeName())
                .employeeAge(employeeRequest.getEmployeeAge())
                .gender(employeeRequest.getGender())
                .salary(employeeRequest.getSalary())
                .build();
    }
}
