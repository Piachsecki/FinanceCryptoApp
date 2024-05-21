package com.piachsecki.financecryptoapp.repository;

import com.piachsecki.financecryptoapp.domain.Article;
import com.piachsecki.financecryptoapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
