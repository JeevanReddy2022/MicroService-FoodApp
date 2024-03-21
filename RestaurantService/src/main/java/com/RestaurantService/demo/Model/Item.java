package com.RestaurantService.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    private String itemName;
    
    
    private String description;
    
    private String ItemimageUrl;

    //@JsonIgnore
    private Integer categoryId;
    
   // private itemType itemType;

    private Double cost;

//    @ManyToMany
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Specify the foreign key column
    @JsonIgnoreProperties("items")
    private Restaurant restaurant;

    
    
//    public enum itemType{
//    	NON_VEG,
//    	VEG
//    }
}
