package com.RestaurantService.demo.Controller;

import com.RestaurantService.demo.DTO.RestaurantDTO;
import com.RestaurantService.demo.DTO.RestaurantsInItemDTO;
import com.RestaurantService.demo.Model.Restaurant;
import com.RestaurantService.demo.Service.RestaurantService;
import feign.FeignException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fooddelivery/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/add")
    public ResponseEntity<RestaurantsInItemDTO> addRestaurant(@RequestBody RestaurantsInItemDTO restaurant){

        RestaurantsInItemDTO registeredRestaurant = restaurantService.addRestaurant(restaurant);

        return new ResponseEntity<>(registeredRestaurant, HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant){

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant);

        return new ResponseEntity<>(updatedRestaurant,HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/{restaurantId}")

    public ResponseEntity<Restaurant> removeRestaurant(@PathVariable Integer restaurantId){

        Restaurant removedRestaurant = restaurantService.removeRestaurant(restaurantId);

        return new ResponseEntity<>(removedRestaurant,HttpStatus.OK);

    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> viewRestaurant(@PathVariable Integer restaurantId){

        RestaurantDTO restaurant = restaurantService.viewRestaurant(restaurantId);

        return new ResponseEntity<>(restaurant,HttpStatus.OK);

    }

//    @GetMapping("/getnearbyRestaurant/{location}")
//    public ResponseEntity<List<Restaurant>> getNearByRestaurant(@PathVariable String location){
//
//        List<Restaurant> restaurants = restaurantService.viewRestaurantByLocation(location);
//
//        return new ResponseEntity<>(restaurants,HttpStatus.OK);
//
//    }

    @GetMapping("/getrestaurantsbyitem/{itemId}")

    public ResponseEntity<List<RestaurantsInItemDTO>> viewRestaurantByItem(@PathVariable Integer itemId){

        List<RestaurantsInItemDTO> restaurants = restaurantService.viewRestaurantByItem(itemId);

        return new ResponseEntity<>(restaurants,HttpStatus.OK);

    }

    @GetMapping("/{restaurantId}/{itemId}")
    public ResponseEntity<Restaurant> addItemToRestaurant(@PathVariable Integer restaurantId,@PathVariable Integer itemId){

        Restaurant restaurant = restaurantService.addItemToRestaurantMenu(itemId,restaurantId);

        return new ResponseEntity<>(restaurant,HttpStatus.OK);

    }

//    public ResponseEntity<String> fallBackRetryHandler(FeignException exc){
//        System.out.println(exc);
//        return new ResponseEntity<>("All retries have been exhausted, please try again later", HttpStatus.SERVICE_UNAVAILABLE);
//    }


}
