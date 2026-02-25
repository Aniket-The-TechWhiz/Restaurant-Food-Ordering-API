package com.resturant.food_ordering.controllers;

import com.resturant.food_ordering.models.MenuItem;
import com.resturant.food_ordering.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItem> addItem(@RequestBody MenuItem item) {
        return ResponseEntity.ok(menuItemService.addMenuItem(item));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAll() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<MenuItem>> getByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getByCategory(id));
    }
}
