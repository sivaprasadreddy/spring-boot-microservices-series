package com.sivalabs.orderservice.web.service;

import com.sivalabs.orderservice.entities.Order;
import com.sivalabs.orderservice.repositories.OrderRepository;
import com.sivalabs.orderservice.util.SampleMockObjectCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  @Mock
  private OrderRepository orderRepository;

  @InjectMocks
  private OrderServiceImpl orderService;

  @Test
  void testSave() {
    Order persistedOrder = SampleMockObjectCreator.getOrder().orElse(new Order());
    persistedOrder.setId(1L);
    Order order = SampleMockObjectCreator.getOrder().get();
    given(orderRepository.save(order)).willReturn(persistedOrder);
    orderService.save(order);
    verify(orderRepository, times(1)).save(order);
  }

  @Test
  void testFindById() {
    given(orderRepository.findById(1L)).willReturn(SampleMockObjectCreator.getOrder());

    orderService.findById(1L);
    verify(orderRepository, times(1)).findById(1L);
  }

}
