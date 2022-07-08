package com.example.demo.model;

import lombok.*;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Purchase {


    User userOrder;
    Cart cartEntity;
    Set<CartItem> cartItemEntities;
    String shippingAddress;
    String status;


}
