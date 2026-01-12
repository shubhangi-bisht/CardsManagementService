package com.maybank.cards.dto;

public class UpdateCardStatusRequestDto
{
    private String status;
    
    private String cardNumber;

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
