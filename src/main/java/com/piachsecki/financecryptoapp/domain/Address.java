package com.piachsecki.financecryptoapp.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;

    @Column(name = "address")
    private String address;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    @JsonBackReference
    private Customer customer;


}
