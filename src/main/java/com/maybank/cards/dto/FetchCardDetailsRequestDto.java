package com.maybank.cards.dto;


public class FetchCardDetailsRequestDto {

    private String cardNumber;

    public String getCardNumber(){
        return cardNumber;
    }

    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }
}
