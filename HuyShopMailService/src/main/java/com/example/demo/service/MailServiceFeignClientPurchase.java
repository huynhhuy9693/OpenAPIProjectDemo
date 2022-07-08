package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@FeignClient(name = "cart-service")
public interface MailServiceFeignClientPurchase {

    @GetMapping(value = "/cart/{orderNumber}")
    Cart findByOrderNumber(@PathVariable String orderNumber);

    @GetMapping(value = "/cart/cart-item/{id}")
    Set<CartItem> findById(@PathVariable Long id);





}