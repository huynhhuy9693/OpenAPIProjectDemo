package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReport {

        private long id;

        private String name;

        private BigDecimal price;

        private String img_url;

        private int quantity;

        private boolean status;


}