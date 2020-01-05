package com.sivalabs.orderservice.repositories;

import com.sivalabs.orderservice.entities.Order;
import com.sivalabs.orderservice.entities.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  void testFindById() {
    List<OrderItem> items = Collections.singletonList(
        OrderItem.builder().productId(1L).quantity(21)
            .productPrice(BigDecimal.valueOf(9.95)).build());
    Order order = Order.builder().customerAddress("dummyAddress")
        .customerEmail("dummyEmail").items(items).build();
    orderRepository.save(order);

    Optional<Order> persistedOrder = orderRepository.findById(1L);
    assertThat(persistedOrder).isPresent();
    assertThat(persistedOrder.get().getId()).isEqualTo(1);
    assertThat(persistedOrder.get().getCustomerAddress()).isEqualTo("dummyAddress");
    assertThat(persistedOrder.get().getCustomerEmail()).isEqualTo("dummyEmail");
    assertThat(persistedOrder.get().getItems()).isNotEmpty().hasSize(1);
    assertThat(persistedOrder.get().getItems().get(0).getId()).isEqualTo(1);
    assertThat(persistedOrder.get().getItems().get(0).getPrice())
        .isEqualTo(BigDecimal.valueOf(208.95));
  }

}
