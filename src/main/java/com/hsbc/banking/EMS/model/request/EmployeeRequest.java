package com.hsbc.banking.EMS.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private String employeeName;
    private Integer employeeAge;
    private String gender;
    private Double salary;
}
