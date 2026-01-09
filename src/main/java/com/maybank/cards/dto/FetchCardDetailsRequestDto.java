package com.maybank.cards.dto;

import java.time.LocalDate;

public class FetchCardDetailsRequestDto {

    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
