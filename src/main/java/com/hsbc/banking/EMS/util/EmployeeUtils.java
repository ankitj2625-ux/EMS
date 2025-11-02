package com.hsbc.banking.EMS.util;

import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeUtils {

    public List<EmployeeResponse> generateEmployees() {
        EmployeeResponse e1 = createEmployee(1L, "ankit", 25);
        EmployeeResponse e2 = createEmployee(2L, "aditya", 30);
        EmployeeResponse e3 = createEmployee(3L, "mohan", 20);
        return List.of(e1, e2, e3);
    }

    private EmployeeResponse createEmployee(Long id, String name, Integer age) {
        return EmployeeResponse.builder().employeeId(id).employeeName(name).employeeAge(age).build();
    }

    public EmployeeResponse generateEmployee(Long id) {
        EmployeeResponse e = createEmployee(id, "rahul", 20);
        return e;
    }
}
