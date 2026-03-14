package com.incture.ecommerce.service;

import com.incture.ecommerce.entity.Order;
import java.util.List;

public interface OrderService {

    Order checkout(Long userId);

    List<Order> getOrders(Long userId);

    Order getOrderById(Long id);

}