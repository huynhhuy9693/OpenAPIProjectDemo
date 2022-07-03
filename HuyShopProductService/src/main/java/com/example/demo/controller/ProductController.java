package com.example.demo.controller;

import com.example.demo.api.ProductApi;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
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
public class ProductController implements ProductApi {
    @Autowired
    private ProductService service;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductEntity>> getAllProduct()
    {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable Long id)
    {
        ProductEntity product = service.findById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> createProduct(
            @Parameter(name = "Product", description = "create new product", required = true)
            @Valid @RequestBody Product product
    ) {

        ProductEntity request= modelMapper.map(product,ProductEntity.class);
        ProductEntity productEntity = service.save(request);
        System.out.println("crete product " + productEntity.getId());
        Product response = modelMapper.map(productEntity,Product.class);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Product> updateProduct(
            @Parameter(name = "id", description = "ID of category to return", required = true) @PathVariable("id") Long id,
            @Parameter(name = "Product", description = "update product", required = true) @Valid @RequestBody Product product
    ) {
        ProductEntity request= modelMapper.map(product,ProductEntity.class);
        ProductEntity productEntity = service.save(request);
        System.out.println("crete product " + productEntity.getId());
        Product response = modelMapper.map(productEntity,Product.class);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @Override

    public ResponseEntity<Void> deleteProduct(
            @Parameter(name = "id", description = "ID of product to return", required = true)
            @PathVariable("id") Long id
    ) {
        System.out.println("delete ID" +id);
        service.findById(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
