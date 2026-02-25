package com.resturant.food_ordering.repository;

import com.resturant.food_ordering.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
