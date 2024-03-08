package com.restaurentservice1.services;

import java.util.List;

import com.restaurentservice.exceptions.RestaurantException;
import com.restaurentservice1.model.Restaurant;

public interface RestaurantService {
	public Restaurant addRestaurant(Restaurant restaurant)throws RestaurantException;
	
	public Restaurant updateRestaurant(Restaurant restaurant)throws RestaurantException;
	
	public Restaurant removeRestaurant(Integer restaurantId)throws RestaurantException;
	
	public Restaurant viewRestaurant(Integer restaurantId)throws RestaurantException;

	public Restaurant findRestaurantById(Integer restaurantId);

	public List<Restaurant> getAllRestaurants();
}
