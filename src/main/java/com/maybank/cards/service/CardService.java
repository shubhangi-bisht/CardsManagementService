package com.maybank.cards.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.maybank.cards.dto.CardDetailsWithTxnResDto;
import com.maybank.cards.dto.CardHolderResponseDto;
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
    
    public Page<CardHolderResponseDto> fetchCardHolderDetails(int page);
    
    public CardDetailsWithTxnResDto getCardDetails(FetchCardDetailsRequestDto requestDto);

}
