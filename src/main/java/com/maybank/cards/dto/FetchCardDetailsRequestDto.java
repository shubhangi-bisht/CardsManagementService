package com.maybank.cards.dto;


public class FetchCardDetailsRequestDto {

    private String accountNumber;

    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
}
