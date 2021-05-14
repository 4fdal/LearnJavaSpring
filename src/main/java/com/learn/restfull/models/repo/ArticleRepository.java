package com.learn.restfull.models.repo;

import com.learn.restfull.models.entities.Article;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    
}
