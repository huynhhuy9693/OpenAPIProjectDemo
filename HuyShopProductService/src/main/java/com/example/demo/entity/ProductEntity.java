package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name="img_url")
    private String imgUrl;

    @Column(name = "quantity")
    private int quantity;

    @Column(name ="status")
    private boolean status;


    @ManyToOne
    @JoinColumn(name = "category_id" )
    private CategoryEntity categoryId;

    @PrePersist
    void onPrePersist() {
        if (status == false) {
            status=true;
        }
    }
}
