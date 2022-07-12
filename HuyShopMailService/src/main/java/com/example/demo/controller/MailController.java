package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.MailService;
import com.example.demo.service.MailServiceFeignClientPurchase;
import com.example.demo.service.MailServiceFeignClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;


import org.springframework.scheduling.annotation.Scheduled;
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
    @PostMapping(value="/send/{orderNumber}",consumes = "text/plain;charset=UTF-8")
    public String sendEmailCartSuccess(@PathVariable ("orderNumber") String orderNumber,@RequestBody  String jsonPurchase ) {
        System.out.println("mail-order");
        Purchase p = new Purchase();
        try{
            service.sendMailPurchaseSuccsess(orderNumber,jsonPurchase);
        }catch (MailException e)
        {
            System.out.println(e);
        }
        return "send mail success";
   }


    @PostMapping(value="/send/notiBeforeDelivery")
    public String sendMailBeforeOneDayDelievery() {
        System.out.println("mail-order");
        Purchase p = new Purchase();
        try{
            service.sendMailBeforeOneDayDelievery();
        }catch (MailException e)
        {
            System.out.println(e);
        }
        return "send mail success";
    }



}
