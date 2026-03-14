package com.incture.ecommerce.controller;

import com.incture.ecommerce.entity.Cart;
import com.incture.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add/{userId}/{productId}")
    public Cart addProduct(@PathVariable Long userId,
                           @PathVariable Long productId,
                           @RequestParam int quantity){

        return cartService.addProductToCart(userId, productId, quantity);
    }

    @PutMapping("/update/{userId}/{productId}")
    public Cart updateProduct(@PathVariable Long userId,
                              @PathVariable Long productId,
                              @RequestParam int quantity){

        return cartService.updateQuantity(userId, productId, quantity);
    }

    @DeleteMapping("/remove/{userId}/{productId}")
    public Cart removeProduct(@PathVariable Long userId,
                              @PathVariable Long productId){

        return cartService.removeProduct(userId, productId);
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId){
        return cartService.getCart(userId);
    }
}