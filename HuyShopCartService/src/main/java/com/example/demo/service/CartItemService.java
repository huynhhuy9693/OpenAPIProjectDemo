package com.example.demo.service;


import com.example.demo.entity.CartItemEntity;
import com.example.demo.model.CartItem;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartItemService {
    Map<Long, CartItemEntity>  maps = new HashMap<>();

    public void addCartItem(CartItemEntity item)
    {

        CartItemEntity cartItemEntity = maps.get(item.getProductId());
        if(cartItemEntity==null)
        {
            maps.put(item.getProductId(), item);
        }else
        {
            cartItemEntity.setQuantity(cartItemEntity.getQuantity()+1);
        }
    }

    public void removeCartItem(Long id)
    {
        maps.remove(id);
    }
    public CartItemEntity updateQuantity(Long productId, Long quantity)
    {
        CartItemEntity cartItem = maps.get(productId);
        cartItem.setQuantity(quantity);
        return cartItem;
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
