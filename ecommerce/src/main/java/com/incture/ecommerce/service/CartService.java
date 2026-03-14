package com.incture.ecommerce.service;

import com.incture.ecommerce.entity.Cart;

public interface CartService {

    Cart getCart(Long userId);

    Cart addProductToCart(Long userId, Long productId, int quantity);

    Cart updateQuantity(Long userId, Long productId, int quantity);

    Cart removeProduct(Long userId, Long productId);

}