package com.example.demo.service;


import com.example.demo.entity.UserEntity;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public List<User> findAll()
    {
        List<UserEntity> request = repository.findAll();
        List<User> userList = new ArrayList<>();
        for (UserEntity u: request) {
            User response = modelMapper.map(u, User.class);
            userList.add(response);
        }
        return userList;
    }

    public User findById(Long id)
    {
        for(UserEntity request : repository.findAll())
        {
            if(request.getId()==id)
            {
                User response = modelMapper.map(request, User.class);
                return response;
            }
        }
        return null;
    }

    public User save(User user)
    {
        UserEntity request = modelMapper.map(user, UserEntity.class);
        UserEntity userEntity = repository.save(request);
        User response = modelMapper.map(userEntity , User.class);
        return response;
    }
    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    public User findByUserName(String userName)
    {
        UserEntity userEntity = repository.findByUserName(userName);
        User user = modelMapper.map(userEntity, User.class);
        return user;

    }


    public UserEntity findByUserId(Long id)
    {
        for(UserEntity request : repository.findAll())
        {
            if(request.getId()==id)
            {
               return request;
            }
        }
        return null;
    }
}
