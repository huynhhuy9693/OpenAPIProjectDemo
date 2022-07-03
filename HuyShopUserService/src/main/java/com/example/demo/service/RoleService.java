package com.example.demo.service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;


    public List<RoleEntity> findAll()
    {
        List<RoleEntity> entityList = repository.findAll();
        return entityList;
    }

    public RoleEntity findById(Long id)
    {
        for(RoleEntity role : repository.findAll())
        {
            if(role.getId()==id)
            {
                return role;

            }
        }
        return null;
    }

    public RoleEntity save(RoleEntity role)
    {
        return repository.save(role);
    }
    public void delete(Long id)
    {
        repository.deleteById(id);
    }
}
