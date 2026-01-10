package com.maybank.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;
import com.maybank.cards.service.CardService;

@RestController
@RequestMapping(value = "/card")
public class CardController {

    //1. Create card
    //2. Fetch Card details
    //3. Update Status of card
    //4. Fetch all card detail of a customer linked to a particular account number - Pagination

    @Autowired
    CardService cardService;

    @PostMapping("/createCard")
    public CreateCardResponseDto createCard(CreateCardRequestDto createCardRequest){
        try{

        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
    }
}
