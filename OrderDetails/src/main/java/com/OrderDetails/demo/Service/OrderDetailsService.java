package com.OrderDetails.demo.Service;

import com.OrderDetails.demo.DTO.AddOrderDetailsDTO;
import com.OrderDetails.demo.DTO.ItemsInRestaurantOrderDTO;
import com.OrderDetails.demo.DTO.OrderDetailsDTO;
import com.OrderDetails.demo.Model.OrderDetails;
import com.OrderDetails.demo.Model.OrderItem;

import java.util.List;

public interface OrderDetailsService {

    public AddOrderDetailsDTO addOrder(Integer cartId);

    public OrderDetails updateOrder(OrderDetails orderDetails);

    public OrderDetails removeOrder(Integer orderId);

    public OrderDetailsDTO viewOrder(Integer orderId);

    public List<OrderDetailsDTO> viewOrderOfCustomer(Integer userId);

	List<ItemsInRestaurantOrderDTO> viewOrderOfRestaurant(Integer restaurantId);

	//List<OrderDetails> viewOrderOfRestaurant(Integer restaurantId);


}
