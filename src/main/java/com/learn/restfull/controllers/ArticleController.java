package com.learn.restfull.controllers;

import com.learn.restfull.models.entities.Article;
import com.learn.restfull.services.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public Iterable<Article> getAllArticle() {
        return this.articleService.getAllArticle();
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable(name = "id") int id) {
        return this.articleService.find(id);
    }

    @PutMapping
    public Article update(@RequestBody Article article) {
        return this.articleService.update(article);
    }

    @PostMapping
    public Article create(@RequestBody Article article) {
        return this.articleService.save(article);
    }

    @DeleteMapping("/{id}")
    public void create(@PathVariable(name = "id") long id) {
        this.articleService.deleteArticle(id);
    }
}
