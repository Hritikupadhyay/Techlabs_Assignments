package com.aurionpro.exceptionHandling;

public class InsufficientStockException extends Exception {
   
	public InsufficientStockException(String message) {
        super(message);
    }
}
