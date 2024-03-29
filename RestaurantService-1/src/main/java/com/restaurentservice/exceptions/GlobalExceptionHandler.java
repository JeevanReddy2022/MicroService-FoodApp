package com.restaurentservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<String> handleRestaurantException(RestaurantException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<String> handleItemException(ItemException ex) {
    	return new ResponseEntity<String>("An unexpected error occured" + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add more exception handlers as needed
}