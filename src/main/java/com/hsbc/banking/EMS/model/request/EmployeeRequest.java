package com.hsbc.banking.EMS.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest {

    private Long employeeId;
    private String employeeName;
    private Integer employeeAge;
    private String gender;
    private Double Salary;
}
