package com.maybank.cards.dto;

import java.time.LocalDate;

public class FetchCardDetailsRequestDto {

    private String accountNumber;

    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
}
