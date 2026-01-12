
package com.maybank.cards.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardTransactionsResponseDto {

    private String cardNumber;
    private int totalTransactions;
    
    @JsonProperty("transactions")
    private List<CardTransactionResponseDto> transactions;


    public CardTransactionsResponseDto(String cardNumber, int totalTransactions,
                                       List<CardTransactionResponseDto> transactions) {
        this.cardNumber = cardNumber;
        this.totalTransactions = totalTransactions;
        this.transactions = transactions;
    }

    // Getters & Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public List<CardTransactionResponseDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CardTransactionResponseDto> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "CardTransactionsResponseDto{" +
                "CardNumber='" + cardNumber + '\'' +
                ", totalTransactions=" + totalTransactions +
                ", transactions=" + transactions +
                '}';
    }
}
