package com.example.demo.controller;

import com.example.demo.api.UserApi;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(value = "/admin-user")
public class UserController implements UserApi {
    @Autowired
    private UserService service;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserEntity>> getAllUsers()
    {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable Long id)
    {
        UserEntity user = service.findById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> createUser(
            @Parameter(name = "User", description = "create new user", required = true) @Valid @RequestBody User user
    ) {

        UserEntity request= modelMapper.map(user,UserEntity.class);
        UserEntity userEntity = service.save(request);
        System.out.println("crete user " + userEntity.getId());
        User response = modelMapper.map(userEntity,User.class);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<User> updateUser(
            @Parameter(name = "id", description = "ID of user to return", required = true) @PathVariable("id") Long id,
            @Parameter(name = "User", description = "update user", required = true) @Valid @RequestBody User user
    ) {
        System.out.println("crete user " + user.getId());
        UserEntity request= modelMapper.map(user,UserEntity.class);
        UserEntity userEntity = service.save(request);
        User response = modelMapper.map(userEntity,User.class);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @Override

    public ResponseEntity<Void> deleteUser(
            @Parameter(name = "id", description = "ID of user to return", required = true) @PathVariable("id") Long id
    ) {
        System.out.println("delete ID" +id);
        service.findById(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
