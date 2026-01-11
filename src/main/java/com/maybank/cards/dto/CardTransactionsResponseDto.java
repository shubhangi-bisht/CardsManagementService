
package com.maybank.cards.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardTransactionsResponseDto {

    private String accountNumber;
    private int totalTransactions;
    
    @JsonProperty("transactions")
    private List<CardTransactionResponseDto> transactions;


    public CardTransactionsResponseDto(String accountNumber, int totalTransactions,
                                       List<CardTransactionResponseDto> transactions) {
        this.accountNumber = accountNumber;
        this.totalTransactions = totalTransactions;
        this.transactions = transactions;
    }

    // Getters & Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
                "accountNumber='" + accountNumber + '\'' +
                ", totalTransactions=" + totalTransactions +
                ", transactions=" + transactions +
                '}';
    }
}
