package com.piachsecki.financecryptoapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonManagedReference
    private List<Invoice> invoices;


    @ManyToMany
    @JoinTable(
            name = "customer_articles",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> favouriteArticles;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//    private List<Wallet> wallets;

}
