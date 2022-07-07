package com.example.demo.controller;


import com.example.demo.dto.Purchase;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(value = "/purchase")
    public ResponseEntity<Purchase> placeOrder(@RequestBody Purchase purchase)
    {
        Purchase purchase1 = cartService.placeOrder(purchase);
        return new ResponseEntity<>(purchase1, HttpStatus.OK);
    }


}
