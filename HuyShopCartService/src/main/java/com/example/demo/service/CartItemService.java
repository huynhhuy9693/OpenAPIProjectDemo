package com.example.demo.service;


import com.example.demo.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartItemService {
    Map<Long, CartItem>  maps = new HashMap<>();

    public void addCartItem(CartItem item)
    {

        CartItem cartItem = maps.get(item.getProductId());
        if(cartItem==null)
        {
            maps.put(item.getProductId(), item);
        }else
        {
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }
    }

    public void removeCartItem(Long id)
    {
        maps.remove(id);
    }
    public CartItem updateQuantity(Long productId, Long quantity)
    {
        CartItem cartItem = maps.get(productId);
        cartItem.setQuantity(quantity);
        return cartItem;
    }
    public void clearCartItem()
    {
        maps.clear();
    }
    public Collection<CartItem> getAllCartItem()
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
