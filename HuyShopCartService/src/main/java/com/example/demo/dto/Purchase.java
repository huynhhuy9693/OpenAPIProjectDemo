package com.example.demo.dto;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;

import lombok.*;

import java.util.Set;

@Setter
@Getter
public class Purchase {

    UserOrder userOrder;
    CartEntity cartEntity;
    Set<CartItemEntity> cartItemEntities;
    String shippingAddress;
    String status;

}
