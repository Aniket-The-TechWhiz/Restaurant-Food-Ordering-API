package com.resturant.food_ordering.services;

import com.resturant.food_ordering.models.Order;
import com.resturant.food_ordering.models.OrderStatus;
import com.resturant.food_ordering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    @Autowired
    private OrderRepository orderRepository;

    // Track explicitly occupied tables (e.g., when guests sit)
    private final java.util.Set<Integer> occupiedTables = new java.util.HashSet<>();
    private final int MAX_TABLES = 10;

    public List<Order> getOrdersForTable(Integer tableNumber) {
        validateTableNumber(tableNumber);
        return orderRepository.findByTableNumber(tableNumber);
    }

    public List<Order> getActiveOrdersForTable(Integer tableNumber) {
        validateTableNumber(tableNumber);
        return orderRepository.findByTableNumberAndStatusNot(tableNumber, OrderStatus.COMPLETED);
    }

    public boolean isTableOccupied(Integer tableNumber) {
        validateTableNumber(tableNumber);
        // A table is occupied either if explicitly marked occupied or it has active orders
        return occupiedTables.contains(tableNumber) || !getActiveOrdersForTable(tableNumber).isEmpty();
    }

    public void freeTable(Integer tableNumber) {
        validateTableNumber(tableNumber);
        // Only allow freeing if there are no active (non-completed) orders
        List<Order> active = getActiveOrdersForTable(tableNumber);
        if (!active.isEmpty()) {
            throw new RuntimeException("Table has active orders and cannot be freed");
        }
        // Delete all orders for this table
        List<Order> allOrders = orderRepository.findByTableNumber(tableNumber);
        orderRepository.deleteAll(allOrders);
        occupiedTables.remove(tableNumber);
    }

    public void occupyTable(Integer tableNumber) {
        validateTableNumber(tableNumber);
        if (occupiedTables.contains(tableNumber)) {
            throw new RuntimeException("Table already occupied");
        }
        occupiedTables.add(tableNumber);
    }

    public java.util.List<com.resturant.food_ordering.controllers.dto.TableStatusResponse> listTables() {
        java.util.List<com.resturant.food_ordering.controllers.dto.TableStatusResponse> list = new java.util.ArrayList<>();
        for (int i = 1; i <= MAX_TABLES; i++) {
            list.add(new com.resturant.food_ordering.controllers.dto.TableStatusResponse(i, isTableOccupied(i)));
        }
        return list;
    }

    private void validateTableNumber(Integer tableNumber) {
        if (tableNumber == null || tableNumber <= 0) {
            throw new RuntimeException("Table number is required");
        }
    }
}
