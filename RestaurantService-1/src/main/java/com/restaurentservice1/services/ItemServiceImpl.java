package com.restaurentservice1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurentservice.exceptions.ItemException;
import com.restaurentservice1.model.Restaurant;
import com.restaurentservice1.model.menuItem;
import com.restaurentservice1.repo.menuItemrepo;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	menuItemrepo itemDAO;
	@Autowired
	RestaurantService restservice;

	
	
	@Override
	public menuItem addItem(menuItem item) throws ItemException {
		Optional<menuItem> opt = itemDAO.findById(item.getItemId());
		if(opt.isPresent()) {
			throw new ItemException("Item already exists..");
		}else {
			return itemDAO.save(item);
		}
	}



	@Override
	public menuItem updateItem(menuItem item) throws ItemException {
		Optional<menuItem> opt = itemDAO.findById(item.getItemId());
		if(opt.isPresent()) {
			return itemDAO.save(item);
		}else {
			throw new ItemException("No such Item found..");
		}
	}



	@Override
	public menuItem viewItem(Integer itemId) throws ItemException {
		Optional<menuItem> opt = itemDAO.findById(itemId);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new ItemException("No Item found with ID: "+itemId);
		}
	}



	@Override
	public menuItem removeItem(Integer itemId) throws ItemException {
		Optional<menuItem> opt = itemDAO.findById(itemId);
		if(opt.isPresent()) {
			menuItem item = opt.get();
			itemDAO.delete(item);
			return item;
		}else {
			throw new ItemException("No Item found with ID: "+itemId);
		}
	}



	@Override
	public List<menuItem> viewAllItems() throws ItemException {
		List<menuItem> items = itemDAO.findAll();
		if(items.size() > 0) {
			return items;
		}else {
			throw new ItemException("No Item exists..");
		}
	}
	
	//modified code

    // Method to add a menuItem to a specific Restaurant
	@Override
    public menuItem addItemToRestaurant(menuItem item, Integer restaurantId) throws ItemException {
        // Retrieve the Restaurant using restaurantId
        Restaurant restaurant = restservice.findRestaurantById(restaurantId);
        if (restaurant == null) {
            throw new ItemException("Restaurant with ID " + restaurantId + " not found");
        }
        item.setRestaurant(restaurant);
        return itemDAO.save(item);
    }

    // Method to get menuItems based on restaurantId
	@Override
    public List<menuItem> getMenuItemsByRestaurantId(Integer restaurantId) {
        return itemDAO.findByRestaurant_RestaurantId(restaurantId);
    }
	

}
