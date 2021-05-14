package com.learn.restfull.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.learn.restfull.models.entities.Category;
import com.learn.restfull.models.repo.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category create(Category category) {
        return this.categoryRepository.save(category);
    }

    public Category update(Category category) {
        return this.categoryRepository.save(category);
    }

    public Iterable<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category get(long id) {

        Optional<Category> category = this.categoryRepository.findById(id);
        if(!category.isPresent()){
            return null ;
        }

        return category.get();
    }

    public void delete(long id) {
        this.categoryRepository.deleteById(id);
    }
}
