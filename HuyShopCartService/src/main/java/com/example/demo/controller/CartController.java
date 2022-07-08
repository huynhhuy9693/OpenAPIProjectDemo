package com.example.demo.controller;


import com.example.demo.dto.CartDTO;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.service.CartService;
import org.apache.http.impl.BHttpConnectionBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(value = "/purchase")
    public ResponseEntity<PurchaseResponse> placeOrder(@RequestBody Purchase purchase)
    {
        PurchaseResponse purchaseResponse = cartService.placeOrder(purchase);
        return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{orderNumber}")
    public ResponseEntity<CartDTO> findByOrderNumber(@PathVariable ("orderNumber") String orderNumber)
    {
        return new ResponseEntity<>(cartService.findByOrderNumber(orderNumber), HttpStatus.OK);
    }


}
