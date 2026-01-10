package com.maybank.cards.util;

import org.springframework.stereotype.Service;

import com.maybank.cards.dto.CardHolderResponseDto;
import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.entity.CardHolder;

@Service
public class Mapper {
	
	CardHolder cardHolder = new CardHolder();
	
	
	public CardHolder toMap(CreateCardRequestDto createCardRequest) {
		cardHolder.setAccountNumber(createCardRequest.getAccountNumber());
		cardHolder.setName(createCardRequest.getName());
		cardHolder.setStatus(createCardRequest.getStatus());
		cardHolder.setExpiryDate(createCardRequest.getExpiryDate());
		
		return cardHolder;
		
	}
	
	public static CardHolderResponseDto toMapPageableRequest(CardHolder cardHolder) {
		CardHolderResponseDto cardHolderResponseDto = new CardHolderResponseDto();
		cardHolderResponseDto.setAccountNumber(cardHolder.getAccountNumber());
		cardHolderResponseDto.setName(cardHolder.getName());
		cardHolderResponseDto.setStatus(cardHolder.getStatus());
		cardHolderResponseDto.setExpiryDate(cardHolder.getExpiryDate());
		
		return cardHolderResponseDto;
		
	}
	
	

}
