package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.MailService;
import com.example.demo.service.MailServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;


@RestController
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    MailServiceFeignClient mailServiceFeignClient;

    @Autowired
    MailService service;


    @PostMapping(value="/send")
    public String sendEmailCreateUser(@RequestBody User user) {
        User modelMail = mailServiceFeignClient.createUser(user);
        user.setEmail(user.getEmail());
        try{
            service.sendMailCreateUser(modelMail);
        }catch (MailException e)
        {
            System.out.println(e);
        }
        return "send mail success";
    }

    @PutMapping(value="/send/{id}")
    public String sendEmailUpdateUser(@RequestBody User user, @PathVariable Long id) {
        User modelMail = mailServiceFeignClient.editUser(id,user);
        user.setEmail(user.getEmail());
        try{
            service.sendMailUpdateUser(modelMail);
        }catch (MailException e)
        {
            System.out.println(e);
        }
        return "send mail success";
    }


    @GetMapping(value = "/home")
    public String home()
    {
        return "hello";
    }
}
