package com.hsbc.banking.EMS.validator;

import com.hsbc.banking.EMS.model.request.EmployeeRequest;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator {

    public void mandatoryParameterCheck(Object parameter, String name) {
        if (parameter == null) {
            throw new IllegalArgumentException(name + ": is mandatory");
        }
    }

    public void parameterTypeCheck(Object parameter, String name, Class<?> type) {
        if (!type.isInstance(parameter)) {
            throw new IllegalArgumentException(name + ": type mismatched. given : " + type.getSimpleName());
        }
    }

    public void validateAge(Integer age) {
        if (age != null && (age < 18 || age > 65)) {
            throw new IllegalArgumentException("Employee age must be between 18 and 65");
        }
    }

    public static void validateGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be null or empty");
        }

        if (!gender.equalsIgnoreCase("MALE") && !gender.equalsIgnoreCase("FEMALE")) {
            throw new IllegalArgumentException("Invalid gender: " + gender + ". Allowed values are MALE or FEMALE.");
        }
    }

    public void validateEmployeeRequest(EmployeeRequest employeeRequest) {
        mandatoryParameterCheck(employeeRequest.getEmployeeName(), "Employee name");
        mandatoryParameterCheck(employeeRequest.getEmployeeAge(), "Employee age");
        mandatoryParameterCheck(employeeRequest.getSalary(), "Employee salary");
        parameterTypeCheck(employeeRequest.getEmployeeName(), "Employee name", String.class);
        parameterTypeCheck(employeeRequest.getEmployeeAge(), "Employee age", Integer.class);
        parameterTypeCheck(employeeRequest.getSalary(), "Employee salary", Double.class);
        validateAge(employeeRequest.getEmployeeAge());
        validateGender(employeeRequest.getGender());
    }
}
