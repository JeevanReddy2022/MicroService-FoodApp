package com.restaurentservice1.controller;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
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

import com.restaurentservice.exceptions.ItemException;
import com.restaurentservice1.model.Restaurant;
import com.restaurentservice1.model.menuItem;
import com.restaurentservice1.services.ItemService;
import com.restaurentservice1.services.RestaurantService;

@RestController
@RequestMapping("/item")
public class ItemServiceController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private RestaurantService restService;
	
	
	
//	@PostMapping("/add/{restaurantId}")
//	public ResponseEntity<menuItem> addItem(@PathVariable Integer restaurantId,@RequestBody menuItem item) throws ItemException{
//		  Restaurant restaurant = restService.findRestaurantById(restaurantId);
//		    if (restaurant == null) {
//		        throw new ItemException("Restaurant with ID " + restaurantId + " not found");
//		    }
//		menuItem newItem = itemService.addItem(item);
//		return new ResponseEntity<menuItem>(newItem, HttpStatus.CREATED);
//	}
	


	//working to add food item to a particular restaurent based on the id
	
	@PostMapping("/add/{restaurantId}")
    public ResponseEntity<?> addItemToRestaurant(@RequestBody menuItem item, @PathVariable Integer restaurantId) {
		
        try {
 // 		  Restaurant restaurant = restService.findRestaurantById(restaurantId);
//		    if (restaurant == null) {
//		        throw new ItemException("Restaurant with ID " + restaurantId + " not found");
//		    }
        	
            menuItem newItem = itemService.addItemToRestaurant(item, restaurantId);
            return new ResponseEntity<>(newItem, HttpStatus.CREATED);
            } catch (ItemException e) {
                String errorMessage = "Restaurant with ID " + restaurantId + " not found.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }      
    
    }
   
    //working to  get food item to a particular restaurent based on the id
    @GetMapping("/items/{restaurantId}")
    public ResponseEntity<List<menuItem>> getMenuItemsByRestaurantId(@PathVariable Integer restaurantId) {
        List<menuItem> menuItems = itemService.getMenuItemsByRestaurantId(restaurantId);
        return new ResponseEntity<List<menuItem>>(menuItems, HttpStatus.OK);
    }
	
	
	//working upadte one item at a time
	@PutMapping("/update")
	public ResponseEntity<menuItem> updateItem(@RequestBody menuItem item) throws ItemException{
		menuItem updatedItem = itemService.updateItem(item);
		return new ResponseEntity<menuItem>(updatedItem, HttpStatus.OK);
	}
	
	
	//working
	@GetMapping("/view/{itemId}")
	public ResponseEntity<menuItem> getItem(@PathVariable("itemId") Integer itemId) throws ItemException{
		menuItem item = itemService.viewItem(itemId);
		return new ResponseEntity<menuItem>(item, HttpStatus.ACCEPTED);
	}
	
	//working 
	@DeleteMapping("/remove/{itemId}")
	public ResponseEntity<menuItem> removeItem(@PathVariable("itemId") Integer itemId) throws ItemException{
		menuItem removedItem = itemService.removeItem(itemId);
		return new ResponseEntity<menuItem>(removedItem, HttpStatus.ACCEPTED);
	}

	//working
	@GetMapping("/viewall")
	public ResponseEntity<List<menuItem>> getAllItems() throws ItemException{
		List<menuItem> items = itemService.viewAllItems();
		return new ResponseEntity<List<menuItem>>(items, HttpStatus.OK);
		
	}
}