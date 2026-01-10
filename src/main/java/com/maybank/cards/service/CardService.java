package com.maybank.cards.service;

import org.springframework.stereotype.Service;

import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;
import com.maybank.cards.dto.FetchCardDetailsRequestDto;
import com.maybank.cards.dto.FetchCardDetailsResponseDto;

@Service
public interface CardService {

    public CreateCardResponseDto createCard(CreateCardRequestDto requestDto);
    
    public FetchCardDetailsResponseDto fetchCardDetails(FetchCardDetailsRequestDto requestDto);

}
