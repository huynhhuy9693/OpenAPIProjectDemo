package com.example.demo.entity;


import com.example.demo.dto.UserOrder;
import lombok.*;

import javax.persistence.*;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;
    @Column(name = "oder_number")
    private String oderNumber;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "status")
    private String status;
    private String shippingAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartEntity")
    private Set<CartItemEntity> cartItemEntities = new HashSet<>();

}
