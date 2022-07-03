package com.example.demo.service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public List<UserEntity> findAll()
    {
        List<UserEntity> entityList = repository.findAll();
        return entityList;
    }

    public UserEntity findById(Long id)
    {
        for(UserEntity user : repository.findAll())
        {
            if(user.getId()==id)
            {
                return user;

            }
        }
        return null;
    }

    public UserEntity save(UserEntity user)
    {
        return repository.save(user);
    }
    public void delete(Long id)
    {
        repository.deleteById(id);
    }
}
