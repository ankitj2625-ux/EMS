package com.hsbc.banking.EMS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
}
