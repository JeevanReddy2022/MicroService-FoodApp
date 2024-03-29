package com.RestaurantService.demo.Repository;

import com.RestaurantService.demo.Model.Restaurant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	Optional<Restaurant> findByRestaurantId(Integer restaurantId);


//    @Query("SELECT new RestaurantsInItemDTO(r.restaurantId, r.restaurantName, r.managerName, r.contact) from Restaurant r INNER JOIN r.items i where i.itemId = :itemId")
//    public List<RestaurantsInItemDTO> getRestaurantsByItem(@Param("itemId") Integer itemId);

}
