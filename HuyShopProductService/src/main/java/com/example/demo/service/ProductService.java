package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;


    public List<ProductEntity> findAll()
    {
        List<ProductEntity> entityList = repository.findAll();
        return entityList;
    }

    public ProductEntity findById(Long id)
    {
        for(ProductEntity product : repository.findAll())
        {
            if(product.getId()==id)
            {
                return product;

            }
        }
        return null;
    }

    public ProductEntity save(ProductEntity product)
    {
        return repository.save(product);
    }
    public void delete(Long id)
    {
        repository.deleteById(id);
    }
}
