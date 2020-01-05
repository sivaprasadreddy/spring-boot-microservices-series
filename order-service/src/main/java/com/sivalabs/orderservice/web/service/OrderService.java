package com.sivalabs.orderservice.web.service;

import java.util.Optional;

import com.sivalabs.orderservice.entities.Order;

public interface OrderService {

  Order save(Order order);

  Optional<Order> findById(Long id);

}
