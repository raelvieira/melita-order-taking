package com.melita.orderingfulfilment.modules.order.repository;

import com.melita.orderingfulfilment.modules.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
