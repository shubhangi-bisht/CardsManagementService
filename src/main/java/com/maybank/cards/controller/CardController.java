package com.maybank.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;
import com.maybank.cards.dto.FetchCardDetailsRequestDto;
import com.maybank.cards.dto.FetchCardDetailsResponseDto;
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

    @PostMapping(value = "/create-card")
    public CreateCardResponseDto createCard(CreateCardRequestDto createCardRequest){
    	CreateCardResponseDto createCardResponseDto = new CreateCardResponseDto();
        try{
        	createCardResponseDto = cardService.createCard(createCardRequest);

        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
        return createCardResponseDto;
    }
    
  //Fetch Card Details for a customer
    @GetMapping(value = "/fetch-card-details")
    public FetchCardDetailsResponseDto fetchCardDetails(FetchCardDetailsRequestDto fetchCardDetailsRequestDto) {
    	FetchCardDetailsResponseDto fetchCardDetailsResponseDto = new FetchCardDetailsResponseDto();
        try{
        	fetchCardDetailsResponseDto = cardService.fetchCardDetails(fetchCardDetailsRequestDto);

        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
        return fetchCardDetailsResponseDto;
    }
    }

