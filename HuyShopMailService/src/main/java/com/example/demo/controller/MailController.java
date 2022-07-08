package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.MailService;
import com.example.demo.service.MailServiceFeignClientPurchase;
import com.example.demo.service.MailServiceFeignClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;



import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import java.util.Set;


@RestController
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    MailServiceFeignClientUser mailServiceFeignClientUser;

    @Autowired
    MailServiceFeignClientPurchase mailServiceFeignClientPurchase;

    @Autowired
    MailService service;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping(value="/send")
    public String sendEmailCreateUser(@RequestBody User user) {
        User modelMail = mailServiceFeignClientUser.createUser(user);
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
        User modelMail = mailServiceFeignClientUser.editUser(id,user);
        user.setEmail(user.getEmail());
        try{
            service.sendMailUpdateUser(modelMail);
        }catch (MailException e)
        {
            System.out.println(e);
        }
        return "send mail success";
    }
    @PostMapping(value="/send/{ordernumber}")
    public String sendEmailCartSuccess(@PathVariable ("ordernumber") String orderNumber , @RequestBody Purchase purchase) {
        System.out.println("mail-order");
        Purchase p = new Purchase();
        try{
            service.sendMailPurchaseSuccse(orderNumber,p);
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
