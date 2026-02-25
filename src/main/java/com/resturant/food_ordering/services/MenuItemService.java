package com.resturant.food_ordering.services;

import com.resturant.food_ordering.models.MenuItem;
import com.resturant.food_ordering.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;
    public MenuItem addMenuItem(MenuItem item) {
        return menuItemRepository.save(item);
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public List<MenuItem> getByCategory(Long id) {
        return menuItemRepository.findByCategoryId(id);
    }
}
