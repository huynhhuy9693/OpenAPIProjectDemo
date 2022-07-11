package com.example.demo.dto;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class Purchase {

    UserOrder userOrder;
    CartDTO cartDTO;
//    List<CartItemDTO> cartItemDTOList;
    String shippingAddress;
    String status;

}
