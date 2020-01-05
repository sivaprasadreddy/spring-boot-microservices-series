package com.sivalabs.orderservice.web.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sivalabs.orderservice.entities.Order;
import com.sivalabs.orderservice.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  
  private final OrderRepository orderRepository;

  @Override
  public Order save(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public Optional<Order> findById(Long id) {
    return orderRepository.findById(id);
  }

}
