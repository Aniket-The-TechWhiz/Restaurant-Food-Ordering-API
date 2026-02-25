package com.resturant.food_ordering.repository;

import com.resturant.food_ordering.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
