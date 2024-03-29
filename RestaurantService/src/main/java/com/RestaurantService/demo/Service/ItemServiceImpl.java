package com.RestaurantService.demo.Service;

//import com.RestaurantService.demo.DTO.ItemDTO;
import com.RestaurantService.demo.DTO.ItemsInRestaurantDTO;
import com.RestaurantService.demo.Exceptions.ItemException;
import com.RestaurantService.demo.Exceptions.RestaurantException;
import com.RestaurantService.demo.Model.Category;
import com.RestaurantService.demo.Model.Item;
import com.RestaurantService.demo.Model.Restaurant;
import com.RestaurantService.demo.Repository.ItemRepository;
import com.RestaurantService.demo.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
	  private static final String ITEM_NOT_FOUND_MESSAGE = "Item does not exist with item id : ";

	    private final ItemRepository itemRepository;
	    private final CategoryService categoryService;
	    private final RestaurantRepository restaurantRepository;

	    @Autowired
	    public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService, RestaurantRepository restaurantRepository) {
	        this.itemRepository = itemRepository;
	        this.categoryService = categoryService;
	        this.restaurantRepository = restaurantRepository;
	    }

//    @Override
//    public ItemsInRestaurantDTO addItem(ItemDTO itemDTO) {
//
//        Category category = categoryService.getCategoryByName(itemDTO.getCategoryName());
//
//        if(category==null) throw new RuntimeException("Category does not exists with name : "+itemDTO.getCategoryName());
//
//        Item item = new Item();
//
//        item.setItemName(itemDTO.getItemName());
//        item.setCost(itemDTO.getCost());
//        item.setCategoryId(category.getCategoryId());
//        item.setRestaurant(itemDTO.getRestaurant());
//        Item savedItem = itemRepository.save(item);
//
//        ItemsInRestaurantDTO savedItemDTO = getDtoFromItem(savedItem);
//
//        return savedItemDTO;
//
//    }
    
	@Override
	public Item addItemToRestaurant(Item item, Integer restaurantId) {
		
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantException("Invalid restaurant id : "+restaurantId));
		 item.setRestaurant(restaurant);
        return itemRepository.save(item);
	}

    @Override
    public ItemsInRestaurantDTO viewItem(Integer itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow(()-> new ItemException(ITEM_NOT_FOUND_MESSAGE+itemId));

        return getDtoFromItem(item);

    }

    @Override
    public ItemsInRestaurantDTO updateItem(Item item) {

        Item validatedItem = itemRepository.findById(item.getItemId()).orElseThrow(()-> new ItemException(ITEM_NOT_FOUND_MESSAGE+item.getItemId()));

        validateCategory(item.getCategoryId());

        Item updatedItem = itemRepository.save(item);

        return getDtoFromItem(updatedItem);
    }

    @Override
    public boolean removeItem(Integer itemId) {

        Item validatedItem = itemRepository.findById(itemId).orElseThrow(()-> new ItemException(ITEM_NOT_FOUND_MESSAGE+itemId));

        itemRepository.delete(validatedItem);

        return true;

    }

    @Override
    public List<Item> viewItemsByCategory(Integer categoryId) {

        Category category = categoryService.getCategoryById(categoryId);

       // if(category==null) throw new RuntimeException("Category does not exists with name :"+categoryId);
        return itemRepository.findByCategoryId(categoryId);

//        List<Item> items = itemRepository.findByCategoryId(category.getCategoryId());
//
//        if(items.isEmpty()) throw new ItemException("Items not found");
//
//        return items;

    }

    @Override
    public List<Item> viewItemsByRestaurant(Integer restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantException("Invalid restaurant id : "+restaurantId));

        //  List<Item> items =  new ArrayList<>(); 
       // if(items.isEmpty()) throw new ItemException("No Items found");
       // return items;
        
        return itemRepository.findByRestaurant_RestaurantId(restaurantId);

    }

    public ItemsInRestaurantDTO getDtoFromItem(Item item){

        ItemsInRestaurantDTO itemDTO = new ItemsInRestaurantDTO();

        itemDTO.setItemId(item.getItemId());

        itemDTO.setItemName(item.getItemName());

        itemDTO.setCost(item.getCost());
        
        itemDTO.setItemImageUrl(item.getItemimageUrl());
        itemDTO.setDescription(item.getDescription());

        Category category = validateCategory(item.getCategoryId());

        itemDTO.setCategory(category);
        itemDTO.setRestaurant(item.getRestaurant());

        return itemDTO;

    }
    
    
    
    //to skip the repeating resturant details 
    public ItemsInRestaurantDTO getDtoFromItemexceptrestAddress(Item item){

        ItemsInRestaurantDTO itemDTO = new ItemsInRestaurantDTO();

        itemDTO.setItemId(item.getItemId());

        itemDTO.setItemName(item.getItemName());

        itemDTO.setCost(item.getCost());
        itemDTO.setItemImageUrl(item.getItemimageUrl());
        itemDTO.setDescription(item.getDescription());

        Category category = validateCategory(item.getCategoryId());

        itemDTO.setCategory(category);
        
        //itemDTO.setRestaurant(item.getRestaurant());

        return itemDTO;

    }
    

    private Category validateCategory(Integer categoryId){

        Category category = categoryService.getCategoryById(categoryId);

        if(category==null) throw new RuntimeException("Category does not exists with id : "+categoryId);

        return category;

    }


}
