package com.maybank.cards.dto;

import java.util.List;

public class CardDetailsWithTxnResDto {
	private String accountNumber;
    
	private FetchCardDetailsResponseDto cardDetails;
   
	private List<CardTransactionResponseDto> transactions;
	
    public String getAccountNumber() {
		return accountNumber;
	}
    
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public FetchCardDetailsResponseDto getCardDetails() {
		return cardDetails;
	}
	
	public void setCardDetails(FetchCardDetailsResponseDto cardDetails) {
		this.cardDetails = cardDetails;
	}
	
	public List<CardTransactionResponseDto> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(List<CardTransactionResponseDto> list) {
		this.transactions = list;
	}
}
