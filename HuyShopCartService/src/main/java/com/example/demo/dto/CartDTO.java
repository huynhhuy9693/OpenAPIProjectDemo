package com.example.demo.dto;

import com.example.demo.entity.CartItemEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {

    private Long id ;
    private UUID oderNumber;
    private Double totalPrice;
    private String status;
    private String shippingAddress;
    private Set<CartItemEntity> cartItemEntities = new HashSet<>();
    private UserOrder userOrder;
}
