package com.learn.restfull.models.repo;

import com.learn.restfull.models.entities.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
