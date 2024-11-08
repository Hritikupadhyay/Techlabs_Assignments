package com.aurionpro.exceptionHandling;

public class DuplicateProductException extends Exception {
    
	public DuplicateProductException(String message) {
        super(message);
    }
}