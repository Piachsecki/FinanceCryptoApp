package com.piachsecki.financecryptoapp.repository;

import com.piachsecki.financecryptoapp.domain.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoCurrency, Long> {
}
