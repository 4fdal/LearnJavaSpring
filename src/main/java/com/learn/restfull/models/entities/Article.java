package com.learn.restfull.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "articles")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @NotEmpty(message = "Title is required")
    @Column(name = "article_title")
    private String title ;

    @NotEmpty(message = "Content is required")
    @Column(name = "article_content", length = 10 * 1024)
    private String content ;

    @Column(name = "article_view")
    private int view ;

    @ManyToOne
    private Category category ;

    public Article(){
        
    }

    public Article(String title, String content){
        this.title = title;
        this.content = content ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "Article [content=" + content + ", id=" + id + ", title=" + title + ", view=" + view + "]";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
}
