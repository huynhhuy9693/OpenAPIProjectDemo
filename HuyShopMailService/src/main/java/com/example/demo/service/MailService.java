package com.example.demo.service;


import com.example.demo.model.Purchase;
import com.example.demo.model.PurchaseDTO;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    public void sendMailPurchaseSuccse(String orderNumber,str purchase)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("huyhuynh9693@gmail.com");
        mailMessage.setSubject("thank you : " +orderNumber);
        mailMessage.setText("thanks");
        javaMailSender.send(mailMessage);
    }

}
