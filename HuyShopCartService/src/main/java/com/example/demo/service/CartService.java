package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;
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
        status ="SUCCES";
        int result = repository.updateStatusByOrdernumber(status,orderNumber);
        return result;
    }

}
