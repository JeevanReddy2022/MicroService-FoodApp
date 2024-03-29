package com.restaurentservice1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
@ToString
@Entity
public class menuItem{	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer itemId;
	@Column(unique = false) // allow dupilicate values
	private String itemName;
	private String Description;
	private Double cost;
	private String itemImage;
	private Category category;
    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Specify the foreign key column
    @JsonIgnoreProperties("itemList")
    private Restaurant restaurant;
	
	public menuItem(
		Integer itemId, String itemName, Integer quantity,String Description, Double cost, String itemImage,Category category,Restaurant restaurant ) {
		
		this.itemId = itemId;
		this.itemName = itemName;
		this.Description = Description;
		this.cost = cost;
		this.category=category;
		this.itemImage=itemImage;
		this.restaurant=restaurant;
	}
	
	
	
}