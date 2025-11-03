package com.hsbc.banking.EMS.util;

import com.hsbc.banking.EMS.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUtils {

//    public List<EmployeeResponse> generateEmployees() {
//        EmployeeResponse e1 = createEmployee(1L, "ankit", 25);
//        EmployeeResponse e2 = createEmployee(2L, "aditya", 30);
//        EmployeeResponse e3 = createEmployee(3L, "mohan", 20);
//        return List.of(e1, e2, e3);
//    }
//
//    private EmployeeResponse createEmployee(Long id, String name, Integer age) {
//        return EmployeeResponse.builder().employeeId(id).employeeName(name).employeeAge(age).build();
//    }
//
//    public EmployeeResponse generateEmployee(Long id) {
//        EmployeeResponse e = createEmployee(id, "rahul", 20);
//        return e;
//    }

    public static boolean validateEmployeeAge(Integer age) {
        return age != null && age >= 18 && age <= 65;
    }

    public static boolean isValidEmployeeName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("^[a-zA-Z ]+$");
    }

    public static boolean isValidSalary(Double salary) {
        return salary != null && salary >= 0;
    }

    public static boolean isValidEmployee(Employee employee) {
        if (employee == null) return false;
        if (employee.getEmployeeName() == null || employee.getEmployeeName().isEmpty()) return false;
        if (employee.getEmployeeAge() == null || employee.getEmployeeAge() <= 18) return false;
        if (employee.getSalary() == null || employee.getSalary() < 0) return false;
        return true;


    }
}
