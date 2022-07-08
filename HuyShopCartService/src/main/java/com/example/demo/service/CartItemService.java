package com.example.demo.service;


import com.example.demo.entity.CartItemEntity;
import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository repository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductFeignClient productFeignClient;

    Map<Long, CartItemEntity>  maps = new HashMap<>();

   public Set<CartItem> findById(Long id)
   {

            Set<CartItem> cartItems = new HashSet<>();
           for(CartItemEntity request : repository.findAll())
           {
               if(request.getId()==id)
               {
                   CartItem response = modelMapper.map(request, CartItem.class);
                   cartItems.add(response);
               }
               return cartItems;
           }
           return null;
       }



    public void removeCartItem(Long id)
    {
        maps.remove(id);
    }
    public CartItemEntity updateQuantity(Long productId)
    {
        CartItemEntity cartItem = maps.get(productId);
        if(cartItem.getQuantity()<=productFeignClient.getQuantityById(productId))
        {
            cartItem.setQuantity(cartItem.getQuantity());
            return cartItem;
        }
        removeCartItem(productId);
        return null;
    }
    public void clearCartItem()
    {
        maps.clear();
    }
    public Collection<CartItemEntity> getAllCartItem()
    {
        return maps.values();
    }
    public int getTotalQuantity()
    {
        return maps.values().size();
    }
    public double getTotalPrice()
    {
        return maps.values().stream().mapToDouble(item->item.getPrice()*item.getQuantity()).sum();
    }
}
