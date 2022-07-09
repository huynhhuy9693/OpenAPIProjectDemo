package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findByName(String name);

    @Query("select p.quantity from ProductEntity p where p.id = :id")
    Integer getQuantityById(@Param("id") Long id);

    @Modifying
    @Query("update ProductEntity p set p.quantity=:quantity where p.id=:id")
    Integer updateProductQuantityForId(@Param("quantity") int quantity, @Param("id") Long id);
}