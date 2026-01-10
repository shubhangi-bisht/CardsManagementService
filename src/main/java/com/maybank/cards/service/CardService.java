package com.maybank.cards.service;

import org.springframework.stereotype.Service;

import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;
import com.maybank.cards.dto.FetchCardDetailsRequestDto;
import com.maybank.cards.dto.FetchCardDetailsResponseDto;
import com.maybank.cards.dto.UpdateCardStatusRequestDto;
import com.maybank.cards.dto.UpdateCardStatusResponseDto;

@Service
public interface CardService {

    public CreateCardResponseDto createCard(CreateCardRequestDto requestDto);
    
    public FetchCardDetailsResponseDto fetchCardDetails(FetchCardDetailsRequestDto requestDto);
    
    public UpdateCardStatusResponseDto updateCardStatus(UpdateCardStatusRequestDto requestDto);

}
