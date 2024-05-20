package com.piachsecki.financecryptoapp.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invoice {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_id;

    @Column(name = "isCostInvoice")
    private boolean isCostInvoice = false;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonBackReference
    private Customer customer;
}
