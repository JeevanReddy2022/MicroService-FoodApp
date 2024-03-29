package com.FoodCartService.demo.DTO;


import com.FoodCartService.demo.Model.Category;
import com.FoodCartService.demo.Model.FoodCart;
import com.FoodCartService.demo.Model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Integer cartItemId;

    private Integer itemId;

    private String itemName;

    private Category category;

    private Integer quantity;

    private Double cost;

    private Restaurant restaurant;

    @JsonIgnore
    private FoodCart foodCart ;

}
