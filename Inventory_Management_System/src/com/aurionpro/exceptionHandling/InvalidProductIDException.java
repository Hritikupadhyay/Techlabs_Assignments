package com.aurionpro.exceptionHandling;

public class InvalidProductIDException extends Exception {
	
	public InvalidProductIDException(String message) {
		super(message);
	}
}