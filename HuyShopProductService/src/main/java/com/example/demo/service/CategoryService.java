package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;


    public List<CategoryEntity> findAll()
    {
        List<CategoryEntity> entityList = repository.findAll();
        return entityList;
    }

    public CategoryEntity findById(Long id)
    {
        for(CategoryEntity category : repository.findAll())
        {
            if(category.getId()==id)
            {
                return category;

            }
        }
        return null;
    }

    public CategoryEntity save(CategoryEntity category)
    {
        return repository.save(category);
    }
    public void delete(Long id)
    {
        repository.deleteById(id);
    }
}
