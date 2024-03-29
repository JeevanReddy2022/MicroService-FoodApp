package com.RestaurantService.demo.DTO;

import com.RestaurantService.demo.Model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantsInItemDTO {

    private Integer restaurantId;

    private String restaurantName;

    private Address address;
    
    private String restaurant_image_Url;

    private String managerName;

    private String contact;
}
