package com.example.demo.dto;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;
import com.example.demo.model.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Purchase {

    UserOrder userOrder;
    CartEntity cartEntity;
    Set<CartItemEntity> cartItemEntities;

}
