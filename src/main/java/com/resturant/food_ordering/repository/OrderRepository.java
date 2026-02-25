package com.resturant.food_ordering.repository;

import com.resturant.food_ordering.models.Order;
import com.resturant.food_ordering.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByTableNumber(Integer tableNumber);
	List<Order> findByTableNumberAndStatusNot(Integer tableNumber, OrderStatus status);
	List<Order> findByStatus(OrderStatus status);
}
