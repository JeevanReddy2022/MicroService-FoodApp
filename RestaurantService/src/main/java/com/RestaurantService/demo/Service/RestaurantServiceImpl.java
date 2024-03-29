package com.RestaurantService.demo.Service;

import com.RestaurantService.demo.DTO.ItemsInRestaurantDTO;
import com.RestaurantService.demo.DTO.RestaurantDTO;
import com.RestaurantService.demo.DTO.RestaurantsInItemDTO;
import com.RestaurantService.demo.Exceptions.AddressException;
import com.RestaurantService.demo.Exceptions.RestaurantException;
import com.RestaurantService.demo.Model.Address;
import com.RestaurantService.demo.Model.Item;
import com.RestaurantService.demo.Model.Restaurant;
import com.RestaurantService.demo.Repository.ItemRepository;
import com.RestaurantService.demo.Repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{

//    @Autowired
//    RestaurantRepository restaurantRepository;
//
//    @Autowired
//    ItemRepository itemRepository;
//
//    @Autowired
//    ItemService itemService;
//
//    @Autowired
//    AddressService addressService;
	
	  private final RestaurantRepository restaurantRepository;
	    private final ItemRepository itemRepository;
	    private final ItemService itemService;
	    private final AddressService addressService;

	    @Autowired
	    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
	                       ItemRepository itemRepository,
	                       ItemService itemService,
	                       AddressService addressService) {
	        this.restaurantRepository = restaurantRepository;
	        this.itemRepository = itemRepository;
	        this.itemService = itemService;
	        this.addressService = addressService;
	    }

    @Override
    public RestaurantsInItemDTO addRestaurant(RestaurantsInItemDTO restaurantDTO) {

        Address address = addressService.saveAddress(restaurantDTO.getAddress());

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        restaurant.setContact(restaurantDTO.getContact());
        restaurant.setAddressId(address.getAddressId());
        restaurant.setManagerName(restaurantDTO.getManagerName());
        restaurant.setRestaurant_image_Url(restaurantDTO.getRestaurant_image_Url());
        

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return getDTOFromRestaurant(savedRestaurant);

    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {

        Restaurant savedRestaurant = validateRestaurant(restaurant.getRestaurantId());

        restaurant.setAddressId(savedRestaurant.getAddressId());

        return restaurantRepository.save(restaurant);

    }

    @Override
    public Restaurant removeRestaurant(Integer restaurantId) {

        Restaurant savedRestaurant = validateRestaurant(restaurantId);

        addressService.deleteAddress(savedRestaurant.getAddressId());

        restaurantRepository.delete(savedRestaurant);

        return savedRestaurant;

    }

    @Override
    public RestaurantDTO viewRestaurant(Integer restaurantId) {

        Restaurant restaurant = validateRestaurant(restaurantId);

        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setRestaurantId(restaurant.getRestaurantId());
        restaurantDTO.setRestaurantName(restaurant.getRestaurantName());
        restaurantDTO.setContact(restaurant.getContact());
        restaurantDTO.setManagerName(restaurant.getManagerName());
        restaurantDTO.setAddress(validateAddress(restaurant.getAddressId()));
        restaurantDTO.setRestaurant_image_Url(restaurant.getRestaurant_image_Url());
        
        List<ItemsInRestaurantDTO> items = new ArrayList<>();

        List<Item> savedItems = restaurant.getItems();

        savedItems.stream().forEach(el-> items.add(itemService.getDtoFromItemexceptrestAddress(el)));

        restaurantDTO.setItems(items);

        return restaurantDTO;
    	
    }

    private RestaurantsInItemDTO getDTOFromRestaurant(Restaurant restaurant){

        RestaurantsInItemDTO savedRestaurantDTO = new RestaurantsInItemDTO();

        savedRestaurantDTO.setRestaurantId(restaurant.getRestaurantId());
        savedRestaurantDTO.setRestaurantName(restaurant.getRestaurantName());
        savedRestaurantDTO.setContact(restaurant.getContact());
        savedRestaurantDTO.setAddress(validateAddress(restaurant.getAddressId()));
        savedRestaurantDTO.setManagerName(restaurant.getManagerName());
        savedRestaurantDTO.setRestaurant_image_Url(restaurant.getRestaurant_image_Url());
        return savedRestaurantDTO;
    }

    private Address validateAddress(Integer addressId){

        Address address = addressService.getAddress(addressId);

        if(address==null) throw new AddressException("Invalid address id : "+addressId);

        return address;

    }

    private Restaurant validateRestaurant(Integer restaurantId){

        return restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantException("Invalid restaurant id : "+restaurantId));

    }

	@Override
	public List<Restaurant> getAllRestaurants() {
	  return restaurantRepository.findAll();
	}
}




	
//	Optional<Restaurant> opt = restaurantRepository.findByRestaurantId(restaurantId);
//	if(opt.isPresent()) {
//		Restaurant restaurant = opt.get();
//		return restaurant;
//	}else {
//		throw new RestaurantException("No Restaurant found with ID: "+restaurantId);
//	}



//@Override
//public List<Restaurant> viewRestaurantByLocation(String location) {
//
//    // PROBLEM : Needs optimization in relationship structure
//
//    return null;
//
//
//}

//@Override
//public List<RestaurantsInItemDTO> viewRestaurantByItem(Integer itemId) {
//
//    Item item = itemRepository.findById(itemId).orElseThrow(()-> new ItemException("Invalid item id : "+itemId));
//
//    List<RestaurantsInItemDTO> restaurantsDto = new ArrayList<>(); // restaurantRepository.getRestaurantsByItem(itemId);
//
//    List<Restaurant> restaurants = item.getRestaurants();
//
//    if(restaurants.isEmpty()) throw new RestaurantException("No restaurant found");
//
//    restaurants.stream().forEach((restaurant -> restaurantsDto.add(getDTOFromRestaurant(restaurant))));
//
//    return restaurantsDto;
//
//}

//@Override
//public Restaurant addItemToRestaurantMenu(Integer itemId, Integer restaurantId) {
//
//    Restaurant restaurant = validateRestaurant(restaurantId);
//
//    Item item = itemRepository.findById(itemId).orElseThrow(()-> new ItemException("Invalid item id : "+itemId));
//
//    item.getRestaurants().add(restaurant);
//
//    restaurant.getItems().add(item);
//
//    itemRepository.save(item);
//
//    return restaurantRepository.save(restaurant);
//
//}




