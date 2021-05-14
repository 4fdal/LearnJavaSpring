package com.learn.restfull.controllers;

import javax.validation.Valid;

import com.learn.restfull.dto.CategoryData;
import com.learn.restfull.dto.ResponseData;
import com.learn.restfull.models.entities.Category;
import com.learn.restfull.services.CategoryService;

import org.modelmapper.ModelMapper;
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
@RequestMapping("/api/v1/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService ;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors){
        
        ResponseData<Category>  responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = this.modelMapper.map(categoryData, Category.class);


        responseData.setPayload(this.categoryService.create(category));
        responseData.setStatus(true);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Category>>> getAll(){
        ResponseData<Iterable<Category>> responseData = new ResponseData<>();
        
        Iterable<Category> categoryIterable = this.categoryService.getAll();
        responseData.setPayload(categoryIterable);
        responseData.setStatus(true);
        
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Category>> get(@PathVariable(name = "id") long id){
        ResponseData<Category> responseData = new ResponseData<>();
        Category category = this.categoryService.get(id);

        responseData.setPayload(category);
        responseData.setStatus(true);

        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = this.modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(this.categoryService.update(category));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> delete(@PathVariable long id){

        this.categoryService.delete(id);
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setStatus(true);

        return ResponseEntity.ok(responseData);
    }
}
