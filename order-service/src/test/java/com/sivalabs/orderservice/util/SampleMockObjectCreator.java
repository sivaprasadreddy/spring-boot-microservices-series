package com.sivalabs.orderservice.util;

import com.sivalabs.orderservice.entities.Order;
import com.sivalabs.orderservice.entities.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class SampleMockObjectCreator {

  public static Optional<Order> getOrder() {
    List<OrderItem> items = List.of(OrderItem.builder().productId(1L).quantity(20).id(11L)
            .productPrice(BigDecimal.valueOf(25.95)).build(),
        OrderItem.builder().productId(2L).quantity(15).id(12L)
            .productPrice(BigDecimal.valueOf(15.95)).build());
    Order order = Order.builder().customerAddress("dummyAddress")
        .customerEmail("dummyEmail@json.com").items(items).id(1L).build();
    return Optional.of(order);
  }
}
