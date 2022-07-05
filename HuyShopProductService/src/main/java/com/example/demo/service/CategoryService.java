package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Category> findAll()
    {
        List<CategoryEntity> request = repository.findAll();
        List<Category> categoryList = new ArrayList<>();
        for (CategoryEntity c: request) {
            Category response = modelMapper.map(c, Category.class);
            categoryList.add(response);
        }
        return categoryList;
    }

    public Category findById(Long id)
    {
        for(CategoryEntity request : repository.findAll())
        {
            if(request.getId()==id)
            {
                Category response = modelMapper.map(request, Category.class);
                return response;
            }
        }
        return null;
    }

    public Category save(Category category)
    {
        for (CategoryEntity c: repository.findAll() )
        {
            if(category.getName().equalsIgnoreCase(c.getName()))
            {
                System.out.println(category.getName() + " is exits ");
                return null;
            }
        }
        CategoryEntity request = modelMapper.map(category, CategoryEntity.class);
        CategoryEntity categoryEntity = repository.save(request);
        Category response = modelMapper.map(categoryEntity , Category.class);
        return response;
    }
    public void delete(Long id)
    {
        repository.deleteById(id);
    }
}
