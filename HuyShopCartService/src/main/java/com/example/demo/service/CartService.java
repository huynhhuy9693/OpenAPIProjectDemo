package com.example.demo.service;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.dto.UserOrder;
import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import io.swagger.annotations.OAuth2Definition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Random;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductFeignClient productFeignClient;
    @Autowired
    CartRepository repository;

    @Autowired
    MailFeignClient mailFeignClient;

    @Autowired
    private ModelMapper modelMapper;

    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC =  digits;
    private static Random generator = new Random();
    public PurchaseResponse placeOrder(Purchase purchase)
    {
        int numberOfCharactor = 6;

        CartEntity cart = purchase.getCartEntity();

        String oderNumber = generateOrderNumber(numberOfCharactor);
        cart.setOderNumber(oderNumber);

        Set<CartItemEntity> cartItemEntities = purchase.getCartItemEntities();
        map<String,int>

        cart.setCartItemEntities(cartItemEntities);
        for (CartItemEntity c: cartItemEntities) {
            if(c.getQuantity()>= productFeignClient.getQuantityById(c.getProductId()))
            {

            }
        }

        UserOrder userOrder = purchase.getUserOrder();
        cart.setUserNameOrder(userOrder.getUserName());

        cart.setShippingAddress(purchase.getShippingAddress());

        cart.setStatus("DELIVERY");

        cart.setEmail(purchase.getUserOrder().getEmail());



        repository.save(cart);
        purchase.setStatus("SUCCESS");

        try{
            System.out.println("send mail");
            mailFeignClient.sendMailSuccess(oderNumber,purchase);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new PurchaseResponse(oderNumber);
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


    public CartDTO findByOrderNumber(String oderNumber)
    {
        CartEntity cart = repository.findByOderNumber(oderNumber);
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        return cartDTO;
    }
}
