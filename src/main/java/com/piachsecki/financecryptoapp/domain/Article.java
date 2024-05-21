package com.piachsecki.financecryptoapp.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "url")
    private String url;
    @Column(name = "title")
    private String title;
    @Column(name = "webPage")
    private String webPage;

    @ManyToMany(mappedBy = "favouriteArticles")
    private List<Customer> customers;

}
