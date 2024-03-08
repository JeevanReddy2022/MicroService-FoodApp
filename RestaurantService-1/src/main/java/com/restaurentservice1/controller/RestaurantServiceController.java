package com.restaurentservice1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurentservice.exceptions.RestaurantException;
import com.restaurentservice1.model.Restaurant;
import com.restaurentservice1.services.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurant")
public class RestaurantServiceController {

	@Autowired
	RestaurantService restService;

//working	

	@PostMapping("/add")
	public ResponseEntity<Restaurant> saveResturant(@Valid @RequestBody Restaurant restaurant)
			throws RestaurantException {
		Integer restaurantId = restaurant.getRestaurantId();
		Restaurant existingRestaurant = restService.findRestaurantById(restaurantId);
		if (existingRestaurant != null) {
			throw new RestaurantException("Restaurant with ID " + restaurant.getRestaurantId() + " already exists");
		}

		Restaurant newRestaurant = restService.addRestaurant(restaurant);

		return new ResponseEntity<Restaurant>(newRestaurant, HttpStatus.CREATED);
	}

	// working update restaurant details
	@PutMapping("/update")
	public ResponseEntity<Restaurant> updateResturant(@Valid @RequestBody Restaurant restaurant)
			throws RestaurantException {

		Restaurant updatedResturant = restService.updateRestaurant(restaurant);

		return new ResponseEntity<Restaurant>(updatedResturant, HttpStatus.ACCEPTED);
	}

	// working
	@DeleteMapping("/remove/{restaurantId}")
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable("restaurantId") Integer restaurantId)
			throws RestaurantException {
		Restaurant removedRestaurant = restService.removeRestaurant(restaurantId);
		return new ResponseEntity<Restaurant>(removedRestaurant, HttpStatus.OK);
	}

//working
	@GetMapping("/view/{restaurantId}")
	public ResponseEntity<Restaurant> getByResturantId(@PathVariable("restaurantId") Integer restaurantId)
			throws RestaurantException {
		Restaurant restaurant = restService.viewRestaurant(restaurantId);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.ACCEPTED);

	}

	// working
	
	@GetMapping("/view/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restService.getAllRestaurants();
        if (restaurants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(restaurants, HttpStatus.OK);
        }
    }


}