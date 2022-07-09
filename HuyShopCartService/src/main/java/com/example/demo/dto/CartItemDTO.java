package com.example.demo.dto;


import com.example.demo.entity.CartEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {

    private Long id;
    private Long productId;
    private Long price;
    private int quantity;
    private CartDTO cartDTO;
}