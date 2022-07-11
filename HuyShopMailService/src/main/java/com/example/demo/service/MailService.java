package com.example.demo.service;


import com.example.demo.model.CartItem;
import com.example.demo.model.Purchase;
import com.example.demo.model.PurchaseDTO;
import com.example.demo.model.User;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender)
    {
        this.javaMailSender=javaMailSender;
    }

    public void sendMailCreateUser(User user)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("register success " + user.getName() );
        mailMessage.setText("Hello new  user : " + user.getUserName()+"-"+"password : "+user.getPassWord());
        javaMailSender.send(mailMessage);
    }

    public void sendMailUpdateUser(User user)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("update success " + user.getName() );
        mailMessage.setText("Hello user : " + user.getUserName()+"-"+"password changed success : "+user.getPassWord());
        javaMailSender.send(mailMessage);
    }

    public void sendMailPurchaseSuccsess(String orderNumber, String jsonPurchase)
    {


        //Gson convert String -> Object
        Gson gson = new Gson();
        Purchase purchase = gson.fromJson(jsonPurchase,Purchase.class);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(purchase.getCartDTO().getEmail());
        mailMessage.setSubject("thank you : " +orderNumber);
        mailMessage.setText("thanks you " + purchase.getUserOrder().getUserName()+
                "<p> ToTal price : "+purchase.getCartDTO().getTotalPrice()+
                "<p> Your Order payment "+purchase.getStatus()+"</p>"+
                "<p>We will delivery your product in 5 days in " + purchase.getShippingAddress()+"</p>"+
                "<p> Please check invoice details and status in website </p> <p> thank you and see your later </p>"
                );
        javaMailSender.send(mailMessage);
    }

//    @Scheduled(cron = "0 */2 * ? * *")
//    public void sendMailBeforeOneDayDelievery(String userName, String jsonPurchase)
//    {
//
//        //Gson convert String -> Object
//        Gson gson = new Gson();
//        Purchase purchase = gson.fromJson(jsonPurchase,Purchase.class);
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(purchase.getCartDTO().getEmail());
//        mailMessage.setSubject("thank you : " +userName);
//        mailMessage.setText("Your product will be delieved tomorrow!! Please check your phone ---Thank");
//        javaMailSender.send(mailMessage);
//    }


}
