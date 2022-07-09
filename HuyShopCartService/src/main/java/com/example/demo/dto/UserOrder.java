package com.example.demo.dto;


import com.example.demo.entity.CartEntity;
import com.example.demo.model.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter


public class UserOrder {

    private Long id;
    private String userName;
    private String address;
    private String phone;
    private  String email;

//    private Set<CartDTO> cartDTOSet = new HashSet<>();
//
//    public void add(CartDTO cartDTO) {
//
//        if (cartDTO != null) {
//
//            if (cartDTOSet == null) {
//                cartDTOSet = new HashSet<>();
//            }
//            cartDTOSet.add(cartDTO);
//            cartDTO.setUserOrder(this);
//        }
//    }
}
