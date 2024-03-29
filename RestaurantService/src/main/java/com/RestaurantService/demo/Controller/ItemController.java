package com.RestaurantService.demo.Controller;
import com.RestaurantService.demo.DTO.ItemsInRestaurantDTO;
import com.RestaurantService.demo.Model.Item;
import com.RestaurantService.demo.Service.ItemService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fooddelivery/items")
public class ItemController {

	private final ItemService itemService;
	
	@Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add/{restaurantId}")
    public ResponseEntity<Item> addItemToRestaurant(@RequestBody Item item,@PathVariable Integer restaurantId){

        Item addedItem = itemService.addItemToRestaurant(item, restaurantId);

        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
        

    }

    
    @PutMapping("/update")
    public ResponseEntity<ItemsInRestaurantDTO> updateItem(@RequestBody Item item){
        ItemsInRestaurantDTO updatedItem = itemService.updateItem(item);

        return new ResponseEntity<>(updatedItem,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Boolean> removeItem(@PathVariable Integer itemId){

        boolean removedItem = itemService.removeItem(itemId);

        return new ResponseEntity<>(removedItem,HttpStatus.OK);

    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemsInRestaurantDTO> viewItemById(@PathVariable Integer itemId){

        ItemsInRestaurantDTO item = itemService.viewItem(itemId);

        return new ResponseEntity<>(item,HttpStatus.OK);

    }

    @GetMapping("/itembycategory/{categoryId}")
    public ResponseEntity<List<Item>> viewItemByCategoryName(@PathVariable Integer categoryId){

        List<Item> items = itemService.viewItemsByCategory(categoryId);

        return new ResponseEntity<>(items,HttpStatus.OK);

    }

    @GetMapping("/itembyrestaurant/{restaurantId}")
    public ResponseEntity<List<Item>> viewItemByRestaurant(@PathVariable Integer restaurantId){

        List<Item> items = itemService.viewItemsByRestaurant(restaurantId);

        return new ResponseEntity<List<Item>>(items,HttpStatus.OK);

    }
//    public ResponseEntity<String> fallBackRetryHandler(FeignException exc){
//        System.out.println(exc);
//        return new ResponseEntity<>("All retries have been exhausted, please try again later", HttpStatus.SERVICE_UNAVAILABLE);
//    }


}
