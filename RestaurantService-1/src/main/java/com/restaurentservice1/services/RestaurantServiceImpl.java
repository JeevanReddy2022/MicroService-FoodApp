package com.restaurentservice1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurentservice.exceptions.RestaurantException;
import com.restaurentservice1.model.Restaurant;
import com.restaurentservice1.repo.Restaurantrepo;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	Restaurantrepo restrepo;
	
	
	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
		Optional<Restaurant> opt = restrepo.findById(restaurant.getRestaurantId());
		if(opt.isPresent()) {
			throw new RestaurantException("Restaurant already exists..");
		}else {
			restaurant.getItemList().forEach(item -> item.setRestaurant(restaurant));
	        
			return restrepo.save(restaurant);
		}
	}


	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantException {
		Optional<Restaurant> opt = restrepo.findById(restaurant.getRestaurantId());
		if(opt.isPresent()) {
			return restrepo.save(restaurant);
		}else {
			throw new RestaurantException("No such Restaurant exists..");
		}
	}


	@Override
	public Restaurant removeRestaurant(Integer restaurantId) throws RestaurantException {
		Optional<Restaurant> opt = restrepo.findById(restaurantId);
		if(opt.isPresent()) {
			Restaurant restaurant = opt.get();
			restrepo.delete(restaurant);
			return restaurant;
		}else {
			throw new RestaurantException("No Restaurant found with ID: "+restaurantId);
		}
	}


	@Override
	public Restaurant viewRestaurant(Integer restaurantId) throws RestaurantException {
		Optional<Restaurant> opt = restrepo.findById(restaurantId);
		if(opt.isPresent()) {
			Restaurant restaurant = opt.get();
			return restaurant;
		}else {
			throw new RestaurantException("No Restaurant found with ID: "+restaurantId);
		}
	}

//working used to add resturant
	@Override
	public Restaurant findRestaurantById(Integer restaurantId) {
		 Optional<Restaurant> optionalRestaurant = restrepo.findByRestaurantId(restaurantId);
	        return optionalRestaurant.orElse(null);
	}


	@Override
	public List<Restaurant> getAllRestaurants() {
		return restrepo.findAll();	
	}
	
	//modified code
	
	
	

}