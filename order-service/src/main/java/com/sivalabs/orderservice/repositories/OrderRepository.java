package com.sivalabs.orderservice.repositories;

import com.sivalabs.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
