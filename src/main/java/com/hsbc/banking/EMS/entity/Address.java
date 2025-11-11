package com.hsbc.banking.EMS.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;


    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Employee employee;
}
