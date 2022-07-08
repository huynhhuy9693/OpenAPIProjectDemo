package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Cart {


    private String oderNumber;
    private Double totalPrice;
    private String status;
    private String shippingAddress;
    private String userNameOrder;
    private String email;
    private Set<CartItem> cartItemEntities = new HashSet<>();

}
