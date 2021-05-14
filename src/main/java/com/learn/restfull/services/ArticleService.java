package com.learn.restfull.services;

import javax.transaction.Transactional;

import com.learn.restfull.models.entities.Article;
import com.learn.restfull.models.repo.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public Article find(long id) {
        return articleRepository.findById(id).get();
    }

    public Article update(Article article) {
        return this.articleRepository.save(article);
    }

    public Iterable<Article> getAllArticle() {
        return this.articleRepository.findAll();
    }

    public void deleteArticle(long id) {
        this.articleRepository.deleteById(id);
    }
}
