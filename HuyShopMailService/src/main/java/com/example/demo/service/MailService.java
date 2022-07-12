package com.example.demo.service;


import com.example.demo.model.*;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;

@Service
public class MailService {
    private JavaMailSender javaMailSender;

    @Autowired
    MailServiceFeignClientPurchase mailServiceFeignClientPurchase;

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
                "-- ToTal price : "+purchase.getCartDTO().getTotalPrice()+
                "-- Your Order payment "+purchase.getStatus()+
                "--We will delivery your product in 5 days in " + purchase.getShippingAddress()+
                "-- Please check invoice details and status in website -- thank you and see your later"
                );
        javaMailSender.send(mailMessage);
    }

    @Scheduled(cron = "0 */5 * ? * *")
    public void sendMailBeforeOneDayDelievery()
    {

        List<Cart> cartList = mailServiceFeignClientPurchase.findByOrderDate(LocalDate.now().minusDays(4));
        if(cartList!=null && mailServiceFeignClientPurchase.findByIsSendingFalse()!=null)
        {
            for(Cart cart : cartList)
            {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(cart.getEmail());
                mailMessage.setSubject("thank you : " + cart.getOderNumber());
                mailMessage.setText("Your product will be delivered tomorrow!! Please check your phone ---Thank");
                javaMailSender.send(mailMessage);
                mailServiceFeignClientPurchase.updateIsSendingTrue(true, cart.getOderNumber());
            }
        }
        System.out.println("not mail send");

    }


}
