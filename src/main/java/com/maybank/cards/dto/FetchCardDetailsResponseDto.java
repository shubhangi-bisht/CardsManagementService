package com.maybank.cards.dto;

import java.time.LocalDate;

public class FetchCardDetailsResponseDto {
    private String name;

    private String accountNumber;

    private LocalDate expiryDate;

    private String status;
    
    private String message;

    public String getStatus(){

        return status;
    }

    public void setStatus(String status){

        this.status = status;
    }

    public LocalDate getExpiryDate(){
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate){
        this.expiryDate = expiryDate;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
