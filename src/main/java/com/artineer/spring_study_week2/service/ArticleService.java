package com.artineer.spring_study_week2.service;

import com.artineer.spring_study_week2.domain.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//비지니스 로직을 담는 곳.
@Service
public class ArticleService {
    private Long id = 0L;
    final List<Article> database = new ArrayList();

    private Long getId() {
        return ++id;
    }

    public Long save(Article request) {
        Article domain = Article.builder()
                .id(getId())
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        database.add(domain);
        return domain.getId();
    }

    public Article findById(Long id) {
        return database.stream().filter(article -> article.getId().equals(id)).findFirst().get();
    }
}
