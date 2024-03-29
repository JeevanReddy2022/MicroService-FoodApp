package com.OrderDetails.demo.DTO;

import com.OrderDetails.demo.Model.FoodCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {

    private Integer orderId;

    private LocalDateTime timeSpan;

    private FoodCart foodCart;

    private String status;
    
  //  private List<OrderItemDTO> items;
}
