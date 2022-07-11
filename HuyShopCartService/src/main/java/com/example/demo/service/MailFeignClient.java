package com.example.demo.service;


import com.example.demo.dto.Purchase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mail-service" )
public interface MailFeignClient {

    @PostMapping(value = "/mail/send/{ordernumber}" )
    Purchase sendMailSuccess(@PathVariable ("ordernumber") String orderNumber,@RequestBody  String jsonPurchase);
}
