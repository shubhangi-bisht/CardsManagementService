package com.maybank.cards.dto;

public class CreateCardResponseDto {

    private String accountNumber;
    private String message;

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
}
