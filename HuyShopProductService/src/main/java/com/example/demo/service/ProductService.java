package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ModelMapper modelMapper;


    public List<Product> findAll()
    {
        List<ProductEntity> request = repository.findAll();
        List<Product> productList = new ArrayList<>();
        for (ProductEntity p: request) {
            Product response = modelMapper.map(p, Product.class);
            productList.add(response);
        }
        return productList;
    }

    public Product findById(Long id)
    {
        for(ProductEntity request : repository.findAll())
        {
            if(request.getId()==id)
            {
                Product response = modelMapper.map(request, Product.class);
                return response;
            }
        }
        return null;
    }

    public Product save(Product product)
    {
        for (ProductEntity p: repository.findAll() )
        {
            if(product.getName().equalsIgnoreCase(p.getName()))
            {
                System.out.println(product.getName() + " is exits ");
                return null;
            }
        }
        ProductEntity request = modelMapper.map(product, ProductEntity.class);
        ProductEntity productEntity = repository.save(request);
        Product response = modelMapper.map(productEntity , Product.class);
        return response;

    }
    public void delete(Long id)
    {
        repository.deleteById(id);
    }
}
