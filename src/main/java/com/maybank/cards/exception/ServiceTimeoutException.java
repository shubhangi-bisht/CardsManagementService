package com.maybank.cards.exception;

public class ServiceTimeoutException extends RuntimeException{
	public ServiceTimeoutException(String message) {
        super(message);
    }
	
}
