package com.security.sample.service;

import com.security.sample.dto.CartQuantityDto;
import com.security.sample.entity.CartItem;
import com.security.sample.exception.NotFoundException;
import com.security.sample.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;


    public CartItem getCartItemById(Long userId) {
        return cartItemRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + userId));
    }

    public CartItem addCartItem(CartItem cartItem, long userId) {
        cartItem.setUserId(userId);
        return cartItemRepository.save(cartItem);
    }


    public String Update(CartQuantityDto cartQuantityDto, long userId, long productId) {

        Optional<CartItem> optionalCartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartQuantityDto.getQuantity());
            cartItemRepository.save(cartItem);
            return "Quantity updated for user " + userId + " and product " + productId;
        } else {
            throw new NotFoundException("User Or product not found");
        }
    }

//DELETE CART ITEM

    public boolean itemDelete(long userId, long productId) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItemRepository.deleteById(cartItem.getId()); // Delete by cart item's ID
            return true;
        } else {
            throw new NotFoundException("Cart item not found for this user and product");
        }
    }

}