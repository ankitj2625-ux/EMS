package com.hsbc.banking.EMS.service;

import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService  {
    public EmployeeResponse getEmployeeById(Long id);
}
