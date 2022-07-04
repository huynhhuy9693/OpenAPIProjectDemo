package com.example.demo.controller;

import com.example.demo.api.RoleApi;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import io.swagger.v3.oas.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/admin-user")
public class RoleController implements RoleApi {
    @Autowired
    private RoleService service;
    @Autowired
    ModelMapper modelMapper;


    public ResponseEntity<List<Role>> getAllRole()
    {
        List<RoleEntity> request = service.findAll();
        List<Role> roleList= new ArrayList<>();
        for (RoleEntity r: request) {
            //map entity to model codegen
            Role response = modelMapper.map(r,Role.class);
            roleList.add(response);
        }

        return new ResponseEntity<>(roleList,HttpStatus.OK);
    }

    public ResponseEntity<Role> findByRoleId(
            @Parameter(name = "id", description = "ID of role to return", required = true)
            @PathVariable("id") Long id
    )
    {
        RoleEntity request = service.findById(id);
        //map entity to model codegen
        Role response = modelMapper.map(request,Role.class);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Role> createRole(
            @Parameter(name = "Role", description = "create new role", required = true) @Valid @RequestBody Role role
    ) {

        RoleEntity request= modelMapper.map(role,RoleEntity.class);
        RoleEntity roleEntity = service.save(request);
        System.out.println("crete role " + roleEntity.getId());
        Role response = modelMapper.map(roleEntity,Role.class);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Role> updateRole(
            @Parameter(name = "id", description = "ID of role to return", required = true) @PathVariable("id") Long id,
            @Parameter(name = "Role", description = "update role", required = true) @Valid @RequestBody Role role
    ){
        System.out.println("update role " + role.getId());
        RoleEntity request= modelMapper.map(role,RoleEntity.class);
        RoleEntity roleEntity = service.save(request);
        Role response = modelMapper.map(roleEntity,Role.class);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @Override

    public ResponseEntity<Void> deleteRole(
            @Parameter(name = "id", description = "ID of role to return", required = true) @PathVariable("id") Long id
    ) {
        System.out.println("delete ID" +id);
        service.findById(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
