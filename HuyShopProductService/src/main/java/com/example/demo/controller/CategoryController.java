package com.example.demo.controller;

import com.example.demo.api.ApiUtil;
//import com.example.demo.api.ProductApi;
import com.example.demo.api.CategoryApi;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@Controller

@RequestMapping(value = "/admin-product")

public class CategoryController implements CategoryApi {

    @Autowired
    private CategoryService service;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(value = "/categories")
    public ResponseEntity<List<CategoryEntity>> getAllCategory()
    {
       return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }
    @GetMapping(value = "/category/{id}")
    public ResponseEntity<CategoryEntity> findById(@PathVariable Long id)
    {
        CategoryEntity category = service.findById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> createCategory(
            @Parameter(name = "Category", description = "create new category", required = true)
            @Valid @RequestBody Category category
    ) {

        CategoryEntity request= modelMapper.map(category,CategoryEntity.class);
        CategoryEntity categoryEntity = service.save(request);
        System.out.println("crete category " + categoryEntity.getId());
        Category response = modelMapper.map(categoryEntity,Category.class);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Category> updateCategory(
            @Parameter(name = "id", description = "ID of category to return", required = true) @PathVariable("id") Long id,
            @Parameter(name = "Category", description = "update category", required = true) @Valid @RequestBody Category category
    ){
        System.out.println("update category " + category.getId());
        CategoryEntity request= modelMapper.map(category,CategoryEntity.class);
        CategoryEntity categoryEntity = service.save(request);
        Category response = modelMapper.map(categoryEntity,Category.class);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @Override

    public ResponseEntity<Void> deleteCategory(
            @Parameter(name = "id", description = "ID of category to return", required = true) @PathVariable("id") Long id
    ) {
        System.out.println("delete ID" +id);
        service.findById(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }





}
