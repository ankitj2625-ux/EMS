package com.hsbc.banking.EMS.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
//@Table(name = "Employee")

public class Employee {

//    @Column(name="emp_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String employeeName;
    private Integer employeeAge;
}
