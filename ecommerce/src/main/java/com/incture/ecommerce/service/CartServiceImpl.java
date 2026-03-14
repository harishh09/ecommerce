package com.incture.ecommerce.service;

import com.incture.ecommerce.entity.*;
import com.incture.ecommerce.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           ProductRepository productRepository,
                           CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart getCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId);

        if(cart == null){
            cart = new Cart();
            cart.setUserId(userId);
            cart.setTotalPrice(0);
            cart.setItems(new ArrayList<>());
            cart = cartRepository.save(cart);
        }

        return cart;
    }

    @Override
    public Cart addProductToCart(Long userId, Long productId, int quantity) {

        Cart cart = getCart(userId);

        Product product = productRepository.findById(productId).orElseThrow();

        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setPrice(product.getPrice() * quantity);
        item.setCart(cart);

        cartItemRepository.save(item);

        List<CartItem> items = cart.getItems();
        items.add(item);
        cart.setItems(items);

        cart.setTotalPrice(cart.getTotalPrice() + item.getPrice());

        return cartRepository.save(cart);
    }

    @Override
    public Cart updateQuantity(Long userId, Long productId, int quantity) {

        Cart cart = getCart(userId);

        for(CartItem item : cart.getItems()){

            if(item.getProductId().equals(productId)){

                double oldPrice = item.getPrice();

                Product product = productRepository.findById(productId).orElseThrow();

                item.setQuantity(quantity);
                item.setPrice(product.getPrice() * quantity);

                cart.setTotalPrice(cart.getTotalPrice() - oldPrice + item.getPrice());

                cartItemRepository.save(item);
            }
        }

        return cartRepository.save(cart);
    }

    @Override
    public Cart removeProduct(Long userId, Long productId) {

        Cart cart = getCart(userId);

        List<CartItem> items = cart.getItems();

        CartItem removeItem = null;

        for(CartItem item : items){
            if(item.getProductId().equals(productId)){
                removeItem = item;
                cart.setTotalPrice(cart.getTotalPrice() - item.getPrice());
            }
        }

        if(removeItem != null){
            items.remove(removeItem);
            cartItemRepository.delete(removeItem);
        }

        cart.setItems(items);

        return cartRepository.save(cart);
    }
}