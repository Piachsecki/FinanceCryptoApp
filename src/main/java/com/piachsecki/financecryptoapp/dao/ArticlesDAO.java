package com.piachsecki.financecryptoapp.dao;

import com.piachsecki.financecryptoapp.domain.Article;

import java.util.List;

public interface ArticlesDAO {
    void addArticleToCustomer(Long customerId, Long articleId);
    void deleteArticle(Long customerId, Long articleId);
    Article findArticleForCustomerById(Long customerId, Long articleId);
    List<Article> findAllArticlesForCustomer(Long id);
}
