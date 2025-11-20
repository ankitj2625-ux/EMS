package com.hsbc.banking.EMS.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    private String departmentName;
    private String location;
    private String managerName;
    private Integer totalEmployees;
//
//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
//    private List<Employee> employees;
}
