package com.restaurentservice1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.restaurentservice.exceptions.GlobalExceptionHandler;
import com.restaurentservice.exceptions.RestaurantException;
import com.restaurentservice1.model.Restaurant;
import com.restaurentservice1.model.menuItem;
import com.restaurentservice1.services.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurant")
public class RestaurantServiceController {

	@Autowired
	RestaurantService restService;

//working	

//	@PostMapping("/add")
//	public ResponseEntity<Restaurant> saveResturant(@Valid @RequestBody Restaurant restaurant)
//			throws RestaurantException {
//		Integer restaurantId = restaurant.getRestaurantId();
//		Restaurant existingRestaurant = restService.findRestaurantById(restaurantId);
//		if (existingRestaurant != null) {
//			throw new RestaurantException("Restaurant with ID " + restaurant.getRestaurantId() + " already exists");
//		}
//
//		Restaurant newRestaurant = restService.addRestaurant(restaurant);
//		return new ResponseEntity<Restaurant>(newRestaurant, HttpStatus.CREATED);
//	}

	 @PostMapping("/add")
	    public ResponseEntity<?> saveResturant(@Valid @RequestBody Restaurant restaurant) {
	        try {
	        	Integer restaurantId = restaurant.getRestaurantId();
	            Restaurant existingRestaurant = restService.findRestaurantById(restaurantId);
	            if (existingRestaurant != null) {
	                throw new RestaurantException("Restaurant with ID " + restaurant.getRestaurantId() + " already exists");
	            }
	            Restaurant newRestaurant = restService.addRestaurant(restaurant);
	            return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
	        }
	        catch (RestaurantException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	        }
	        catch(MethodArgumentTypeMismatchException ex) {
	        	 String errorMessage = "Invalid method argument type: " + ex.getName() + ", value: " + ex.getValue() + ", required type: " + ex.getRequiredType();
	             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	         	
	        }
    }
	
	// working update restaurant details
	@PutMapping("/update")
	public ResponseEntity<?> updateResturant(@Valid @RequestBody Restaurant restaurant)
			throws RestaurantException {
try {
		Restaurant updatedRestaurant = restService.updateRestaurant(restaurant);

		return new ResponseEntity<Restaurant>(updatedRestaurant, HttpStatus.ACCEPTED);
	}catch (RestaurantException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}

	// working
	@DeleteMapping("/remove/{restaurantId}")
	public ResponseEntity<?> deleteRestaurant(@PathVariable("restaurantId") Integer restaurantId)
	        throws RestaurantException {
	    try {
	        restService.removeRestaurant(restaurantId);
	        String message = "Restaurant with Id " + restaurantId + " deleted successfully";
	        return ResponseEntity.ok().body(message);
	    } catch (RestaurantException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}


//working
	@GetMapping("/view/{restaurantId}")
	public ResponseEntity<?> getByResturantId(@PathVariable("restaurantId") Integer restaurantId)
			throws RestaurantException {
		try {
		Restaurant restaurant = restService.viewRestaurant(restaurantId);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.ACCEPTED);

	}catch (RestaurantException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}

	// working

	@GetMapping("/view/all")
	public ResponseEntity<?> getAllRestaurants() {
		try {
		List<Restaurant> restaurants = restService.getAllRestaurants();
		if (restaurants.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(restaurants, HttpStatus.OK);
		}
		}catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    
	}
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
}