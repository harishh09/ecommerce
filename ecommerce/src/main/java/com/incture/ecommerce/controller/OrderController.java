package com.incture.ecommerce.controller;

import com.incture.ecommerce.entity.Order;
import com.incture.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout/{userId}")
    public Order checkout(@PathVariable Long userId){
        return orderService.checkout(userId);
    }

    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable Long userId){
        return orderService.getOrders(userId);
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
}