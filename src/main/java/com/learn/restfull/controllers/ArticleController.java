package com.learn.restfull.controllers;

import javax.validation.Valid;

import com.learn.restfull.dto.ResponseData;
import com.learn.restfull.models.entities.Article;
import com.learn.restfull.services.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
    public ResponseEntity<ResponseData<Article>> create( @Valid @RequestBody Article article, Errors errors) {

        ResponseData<Article> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null); 
            // throw new RuntimeException("Validate Errors");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        this.articleService.save(article);
        responseData.setStatus(true);
        responseData.setPayload(article);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void create(@PathVariable(name = "id") long id) {
        this.articleService.deleteArticle(id);
    }
}
