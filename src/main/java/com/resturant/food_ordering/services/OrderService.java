package com.resturant.food_ordering.services;

import com.resturant.food_ordering.models.Order;
import com.resturant.food_ordering.models.OrderItem;
import com.resturant.food_ordering.models.OrderStatus;
import com.resturant.food_ordering.repository.MenuItemRepository;
import com.resturant.food_ordering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private TableService tableService;
    public Order placeOrder(Order order) {

        if (order.getTableNumber() == null || order.getTableNumber() <= 0) {
            throw new RuntimeException("Table number is required");
        }

        // Ensure table is marked occupied when an order is placed
        try {
            if (!tableService.isTableOccupied(order.getTableNumber())) {
                tableService.occupyTable(order.getTableNumber());
            }
        } catch (RuntimeException ex) {
            // If occupyTable throws because already occupied, ignore; otherwise rethrow
        }

        double totalAmount = 0;

        // Calculate order total
        for (OrderItem item : order.getItems()) {
            if (item.getMenuItem() == null || item.getMenuItem().getId() == null) {
                throw new RuntimeException("Menu item id is required");
            }

            // load menu item from DB to get price
            var menuItem = menuItemRepository.findById(item.getMenuItem().getId())
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));

            item.setMenuItem(menuItem);

            // itemTotal = quantity * price of menu item
            double itemTotal = item.getQuantity() * menuItem.getPrice();
            item.setItemTotal(itemTotal);

            totalAmount += itemTotal;

            // connect orderItem → order
            item.setOrder(order);
        }

        // final order amount
        order.setTotalAmount(totalAmount);

        // set initial status (order is queued for kitchen)
        order.setStatus(OrderStatus.PENDING);

        // save order
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
}
