package com.maybank.cards.service;

import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;

public interface CardService {

    public CreateCardResponseDto createCard(CreateCardRequestDto requestDto);

}
