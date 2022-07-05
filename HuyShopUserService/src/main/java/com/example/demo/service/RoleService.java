package com.example.demo.service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Role> findAll()
    {
        List<RoleEntity> request = repository.findAll();
        List<Role> roleList = new ArrayList<>();
        for (RoleEntity r: request) {
            Role response = modelMapper.map(r, Role.class);
            roleList.add(response);
        }
        return roleList;
    }

    public Role findById(Long id)
    {
        for(RoleEntity request : repository.findAll())
        {
            if(request.getId()==id)
            {
                Role response = modelMapper.map(request, Role.class);
                return response;
            }
        }
        return null;
    }

    public Role save(Role role)
    {
        RoleEntity request = modelMapper.map(role, RoleEntity.class);
        RoleEntity roleEntity = repository.save(request);
        Role response = modelMapper.map(roleEntity , Role.class);
        return response;
    }
    public void delete(Long id)
    {
        repository.deleteById(id);
    }
}
