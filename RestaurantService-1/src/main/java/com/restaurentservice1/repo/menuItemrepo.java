package com.restaurentservice1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurentservice1.model.menuItem;

@Repository
public interface menuItemrepo extends JpaRepository<menuItem, Integer>{
	List<menuItem> findByRestaurant_RestaurantId(Integer restaurantId);
}