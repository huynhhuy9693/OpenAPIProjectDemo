package com.example.demo.service;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.UserOrder;
import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Random;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    UserFeignClient userFeignClient;
    @Autowired
    CartItemService cartItemService;

    @Autowired
    private ModelMapper modelMapper;

    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC =  digits;
    private static Random generator = new Random();
    public Purchase placeOrder(Purchase purchase)
    {
        int numberOfCharactor = 6;
        CartEntity cart = purchase.getCartEntity();
        String oderNumber = generateOrderNumber(numberOfCharactor);
        cart.setOderNumber(oderNumber);

        Set<CartItemEntity> cartItemEntities = purchase.getCartItemEntities();

        cartItemEntities.forEach(item->cartItemService.addCartItem(item));

        UserOrder userOrder = purchase.getUserOrder();

        cart.setShippingAddress(userOrder.getAddress());

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        userOrder.add(cartDTO);

        try{
            System.out.println("send mail");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new Purchase();
    }
    private String generateOrderNumber(int numberOfCharactor) {
//		return UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    private  int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

}
