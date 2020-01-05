package com.sivalabs.orderservice.web.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sivalabs.orderservice.entities.Order;
import com.sivalabs.orderservice.web.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/api/orders")
  public Order createOrder(@RequestBody Order order) {
    return orderService.save(order);
  }

  @GetMapping("/api/orders/{id}")
  public Optional<Order> findOrderById(@PathVariable Long id) {
    return orderService.findById(id);
  }

}
