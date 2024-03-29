package com.OrderDetails.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OrderDetails.demo.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByRestaurantId(Integer restaurantId);

}
