package com.restaurentservice1.services;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.restaurentservice.exceptions.ItemException;
import com.restaurentservice1.model.menuItem;

public interface ItemService {
	
	public menuItem addItem(menuItem item)throws ItemException;
	
	public menuItem updateItem(menuItem item)throws ItemException;
	
	public menuItem viewItem(Integer itemId)throws ItemException;
	
	public menuItem removeItem(Integer itemId)throws ItemException;
	
	public List<menuItem> viewAllItems()throws ItemException;

	public menuItem addItemToRestaurant(menuItem item, Integer restaurantId) throws ItemException;

	public List<menuItem> getMenuItemsByRestaurantId(Integer restaurantId);

	//menuItem addItem(menuItem item) throws com.restaurentservice1.services.ItemException;

	

}
