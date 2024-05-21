package com.piachsecki.financecryptoapp.repository;

import com.piachsecki.financecryptoapp.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticlesRepository extends JpaRepository<Article, Long> {

}
