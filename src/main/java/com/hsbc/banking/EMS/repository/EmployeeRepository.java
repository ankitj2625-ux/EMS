package com.hsbc.banking.EMS.repository;

import com.hsbc.banking.EMS.entity.Employee;
import com.hsbc.banking.EMS.model.response.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findEmployeeByGender(String gender);

    List<EmployeeResponse> findByEmployeeAge(Integer age);
}
