package com.hsbc.banking.EMS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Table(name = "Employee")
@Getter
@Setter
@ToString
public class Employee {

    //    @Column(name="emp_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String employeeName;
    private Integer employeeAge;
    private String gender;
    private Double salary;
    //This is a sample text to understand git conflict

    private Department department;
}
