package com.hsbc.banking.EMS.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties
public class EmployeeResponse {
    private Long employeeId;
    private String employeeName;
    private Integer employeeAge;
}
