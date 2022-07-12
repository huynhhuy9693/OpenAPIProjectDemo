package com.example.demo.service;


import com.example.demo.config.MailConsumerConfig;
import com.example.demo.model.MailModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class MailService implements RabbitListenerConfigurer {

    private JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void doSend(MailModel mail) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        mailSender.send(message);
        logger.info("Send mail to ...."+ mail.getTo());
    }
}
