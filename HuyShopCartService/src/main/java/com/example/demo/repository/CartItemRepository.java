package com.example.demo.repository;

import com.example.demo.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {

    Set<CartItemEntity> findByCartEntity(Long cartEntity);
}
