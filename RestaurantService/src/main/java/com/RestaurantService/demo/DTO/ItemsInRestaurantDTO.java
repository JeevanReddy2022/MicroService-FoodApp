package com.RestaurantService.demo.DTO;


import com.RestaurantService.demo.Model.Category;
import com.RestaurantService.demo.Model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemsInRestaurantDTO {

    private Integer itemId;

    private String itemName;

    private Category category;

    private Double cost;
    
    private String ItemImageUrl;
    
    private String description;
    

    private Restaurant restaurant;

}
