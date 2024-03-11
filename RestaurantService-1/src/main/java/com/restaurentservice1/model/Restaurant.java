package com.restaurentservice1.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
public class Restaurant {
	
	
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private Long Id;
	private Integer restaurantId;
	private String restaurantName;
	private String address;
    private String restaurantCity;
    private String restaurantState;
    private String restaurantZipcode;
    private String restaurantImage;
    private String phoneNumber;
	
	//@OneToMany(targetEntity = menuItem.class,cascade = CascadeType.ALL)
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)    
	private List<menuItem> itemList = new ArrayList<>();

	public Restaurant(
//			Long id, 
			Integer restaurantId, String restaurantName, String address,String restaurantCity,String restaurantState,
			String restaurantZipcode,String restaurantImage,String phoneNumber,List<menuItem> itemList) {
//		this.Id=id;
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantCity=restaurantCity;
		this.restaurantState=restaurantState;
		this.restaurantZipcode=restaurantZipcode;
		this.address = address;
		this.restaurantImage=restaurantImage;
		this.phoneNumber=phoneNumber;
		this.itemList = itemList;
	}

	public Restaurant() {
		
	}

	
	
//	public Long getId() {
//		return Id;
//	}
//
//	public void setId(Long id) {
//		Id = id;
//	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

	public String getRestaurantCity() {
		return restaurantCity;
	}

	public void setRestaurantCity(String restaurantCity) {
		this.restaurantCity = restaurantCity;
	}

	public String getRestaurantState() {
		return restaurantState;
	}

	public void setRestaurantState(String restaurantState) {
		this.restaurantState = restaurantState;
	}

	public String getRestaurantZipcode() {
		return restaurantZipcode;
	}

	public void setRestaurantZipcode(String restaurantZipcode) {
		this.restaurantZipcode = restaurantZipcode;
	}

	
	public String getRestaurantImage() {
		return restaurantImage;
	}

	public void setRestaurantImage(String restaurantImage) {
		this.restaurantImage = restaurantImage;
	}
	
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<menuItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<menuItem> itemList) {
		this.itemList = itemList;
	}
	
	
}