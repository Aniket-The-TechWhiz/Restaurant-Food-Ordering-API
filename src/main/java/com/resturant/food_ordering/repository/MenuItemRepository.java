package com.resturant.food_ordering.repository;

import com.resturant.food_ordering.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
    List<MenuItem> findByCategoryId(Long categoryId);
}
