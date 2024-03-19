package com.RestaurantService.demo.Service;

//import com.RestaurantService.demo.DTO.ItemDTO;
import com.RestaurantService.demo.DTO.ItemsInRestaurantDTO;
import com.RestaurantService.demo.Model.Item;

import java.util.List;

public interface ItemService {

    //public ItemsInRestaurantDTO addItem(ItemDTO itemDTO);

    public ItemsInRestaurantDTO viewItem(Integer itemId);

    public ItemsInRestaurantDTO updateItem(Item item);

    public boolean removeItem(Integer itemId);

    public List<Item> viewItemsByCategory(Integer categoryId);
    
    public Item addItemToRestaurant(Item item,Integer restaurantId);
    
    public List<Item> viewItemsByRestaurant(Integer restaurantId);

    public ItemsInRestaurantDTO getDtoFromItem(Item item);

	public ItemsInRestaurantDTO getDtoFromItemexceptrestAddress(Item el);


}
