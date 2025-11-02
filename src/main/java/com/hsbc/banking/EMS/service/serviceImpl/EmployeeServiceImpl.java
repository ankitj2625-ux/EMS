package com.hsbc.banking.EMS.service.serviceImpl;

import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import com.hsbc.banking.EMS.util.EmployeeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl {

    private final EmployeeUtils employeeUtils;

    public EmployeeResponse getEmployeeById(Long id) {
        return employeeUtils.generateEmployee(id);
    }
}
