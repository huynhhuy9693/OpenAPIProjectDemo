package com.example.demo.repository;

import com.example.demo.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {

    Set<CartItemEntity> findByCartEntity(Long cartEntity);

    @Query("SELECT SUM(c.quantity) FROM CartItemEntity c WHERE c.productId=:productId")
    Integer countCartItemByProductId(@Param("productId") Long productId);
}
