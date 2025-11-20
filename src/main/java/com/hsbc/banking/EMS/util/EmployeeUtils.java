package com.hsbc.banking.EMS.util;

import com.hsbc.banking.EMS.entity.Employee;
import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeUtils {

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
