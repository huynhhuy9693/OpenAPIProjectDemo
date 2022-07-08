package com.example.demo.controller;


import com.example.demo.model.CartItem;
import com.example.demo.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/cart")
public class CartItemController {

    @Autowired
    CartItemService service;

    @GetMapping(value = "/cart-item/{id}")
    public ResponseEntity <Set<CartItem>> findById(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
