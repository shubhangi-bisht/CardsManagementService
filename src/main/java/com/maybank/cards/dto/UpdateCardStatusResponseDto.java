package com.maybank.cards.dto;

public class UpdateCardStatusResponseDto {
    private String status;

    private String message;

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
