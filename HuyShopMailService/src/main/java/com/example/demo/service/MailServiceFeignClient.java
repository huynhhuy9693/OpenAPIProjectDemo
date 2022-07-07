package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface MailServiceFeignClient {

    @PostMapping("/admin-user/user")
    User createUser(@RequestBody User user);
    @PutMapping(value = "/admin-user/user/{id}")
    User editUser(@PathVariable Long id,@RequestBody User user);


}