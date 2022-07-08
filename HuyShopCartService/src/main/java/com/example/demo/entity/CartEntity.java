package com.example.demo.entity;


import com.example.demo.dto.UserOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "cart")
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
    private String userNameOrder;
    private String email;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartEntity")
    private Set<CartItemEntity> cartItemEntities = new HashSet<>();

    public void add(CartItemEntity item) {

        if (item != null) {
            if(cartItemEntities == null) {
                cartItemEntities = new HashSet<>();
            }
        }
        cartItemEntities.add(item);
        item.setCartEntity(this);
    }

}
