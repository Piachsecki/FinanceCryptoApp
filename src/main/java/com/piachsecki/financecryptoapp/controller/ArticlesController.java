package com.piachsecki.financecryptoapp.controller;


import com.piachsecki.financecryptoapp.domain.Article;
import com.piachsecki.financecryptoapp.service.ArticlesService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticlesController {

    private final ArticlesService articlesService;

    @GetMapping
    public ResponseEntity<List<Article>> getArticles(
            @RequestParam List<String> industries
            ){
        List<Article> articles = articlesService.getArticlesFromTheSpecifiedFields(industries);
        return ResponseEntity.ok().body(articles);
    }


    @PostMapping("/{customerId}/{articleId}")
    public ResponseEntity<Void> addArticleToCustomer(
            @PathVariable Long customerId,
            @PathVariable Long articleId
    ){
        articlesService.addArticleToCustomer(customerId, articleId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
