package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;
import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javax.transaction.Transactional;

import java.util.*;

@Service
public class CartService {

   @Autowired
   private ProductFeignClient productFeignClient;
    @Autowired
    CartRepository repository;

    @Autowired
    MailFeignClient mailFeignClient;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartItemService cartItemService;

    public CartDTO findByOrderNumber(String oderNumber)
    {
        CartEntity cart = repository.findByOderNumber(oderNumber);
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        return cartDTO;
    }

    @Transactional
    public Integer updateStatusByOrdernumber(String status , String orderNumber)
    {
        status ="SUCCESS";
        int result = repository.updateStatusByOrdernumber(status,orderNumber);
        return result;
    }

    public List<Cart> findAll()
    {
        List<CartEntity> request = repository.findAll();
        List<Cart> cartList = new ArrayList<>();
        for (CartEntity c: request) {
            Cart response = modelMapper.map(c, Cart.class);
            cartList.add(response);
        }
        return cartList;
    }

    public Long sumTotalPrice()
    {
        Long result = repository.sumTotalPrice();
        return result;
    }




}
