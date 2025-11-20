package com.hsbc.banking.EMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employee")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    //    @Column(name="emp_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long employeeId;
    @Column
    private String employeeName;
    @Column
    private Integer employeeAge;
    @Column
    private String gender;
    @Column
    private Double salary;
    //This is a sample text to understand git conflict

//    private Department department;
}
