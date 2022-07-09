package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;
import com.example.demo.model.Cart;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.sun.jdi.InvocationException;
import io.swagger.annotations.OAuth2Definition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;


import java.lang.reflect.InvocationTargetException;
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

    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC =  digits;
    private static Random generator = new Random();
    public PurchaseResponse placeOrder(Purchase purchase)
    {
        int numberOfCharactor = 6;


        CartDTO cartDTO = purchase.getCartDTO();

        String oderNumber = generateOrderNumber(numberOfCharactor);
        cartDTO.setOderNumber(oderNumber);

        List<CartItemDTO> cartItemDTOList = purchase.getCartItemDTOList();
        cartDTO.setCartItemDTOList(cartItemDTOList);

            // check quantity purchase -> quantity shop
            for (int i =0;i<cartItemDTOList.size();i++)
            {
                int quantity = productFeignClient.getQuantityById(cartItemDTOList.get(i).getProductId());
                {
                    if (quantity <= cartItemDTOList.get(i).getQuantity()) {
                        cartItemDTOList.remove(cartItemDTOList.get(i));
                        i--;
                    }
                }
            }


        UserOrder userOrder = purchase.getUserOrder();
        cartDTO.setUserNameOrder(userOrder.getUserName());
        cartDTO.setShippingAddress(purchase.getShippingAddress());
        cartDTO.setStatus("DELIVERY");
        cartDTO.setEmail(purchase.getUserOrder().getEmail());

        //save DB
        CartEntity cart = modelMapper.map(cartDTO,CartEntity.class);
        List<CartItemEntity> cartItemEntityList = new ArrayList<>();
        for (CartItemDTO cartItemDTO : cartItemDTOList)
        {
            CartItemEntity cartItemEntity = modelMapper.map(cartItemDTO,CartItemEntity.class);
            cartItemEntityList.add(cartItemEntity);
            cart.add(cartItemEntity);
        }

        //update quantity shop
        for (CartItemEntity cartItem:cartItemEntityList) {
            int quantity = cartItem.getQuantity();
            System.out.println(quantity);
            int quantityShop = productFeignClient.getQuantityById(cartItem.getProductId());
            System.out.println(quantityShop);
            int result = (quantityShop-quantity);
            System.out.println(result);
            productFeignClient.updateProductQuantityForId(result, cartItem.getProductId());
        }
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
