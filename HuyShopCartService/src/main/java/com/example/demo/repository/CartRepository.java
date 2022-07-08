package com.example.demo.repository;

import com.example.demo.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
CartRepository extends JpaRepository<CartEntity,Long> {

    CartEntity findByOderNumber(String oderNumber);

    CartEntity findByUserNameOrder(String userNameOrder);


}
