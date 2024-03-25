package com.OrderDetails.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrderDetails.demo.DTO.OrderDetailsDTO;
import com.OrderDetails.demo.Model.OrderDetails;
import com.OrderDetails.demo.Service.OrderDetailsService;

@RestController
@RequestMapping("/fooddelivery/orderdetails")
public class OrderDetailsController {

    @Autowired
    OrderDetailsService orderDetailsService;

    @PostMapping("/{cartId}")
  
    public ResponseEntity<OrderDetails> addOrder( @PathVariable Integer cartId){

        OrderDetails savedOrder = orderDetailsService.addOrder(cartId);

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);

    }

    @PutMapping
    
    public ResponseEntity<OrderDetails> updateOrder(@RequestBody OrderDetails orderDetails){

        OrderDetails updatedOrder = orderDetailsService.updateOrder(orderDetails);

        return new ResponseEntity<>(updatedOrder,HttpStatus.ACCEPTED);

    }


    @GetMapping("/{orderId}")
    
    public ResponseEntity<OrderDetailsDTO> viewOrder(@PathVariable Integer orderId){

        OrderDetailsDTO orderDetailsdto = orderDetailsService.viewOrder(orderId);

        return new ResponseEntity<>(orderDetailsdto,HttpStatus.OK);

    }



}
