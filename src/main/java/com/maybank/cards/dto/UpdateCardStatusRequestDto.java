package com.maybank.cards.dto;

public class UpdateCardStatusRequestDto
{
    private String status;

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
