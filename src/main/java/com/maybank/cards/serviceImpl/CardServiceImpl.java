package com.maybank.cards.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;
import com.maybank.cards.dto.FetchCardDetailsRequestDto;
import com.maybank.cards.dto.FetchCardDetailsResponseDto;
import com.maybank.cards.repository.CardRepository;
import com.maybank.cards.service.CardService;
import com.maybank.cards.util.Mapper;

@Service
public class CardServiceImpl implements CardService{

	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	Mapper mapper;
	
	//Create Card for a customer
	@Override
	public CreateCardResponseDto createCard(CreateCardRequestDto requestDto) {
		CreateCardResponseDto createCardResponseDto = new CreateCardResponseDto();
		cardRepository.save(mapper.toMap(requestDto));
		return createCardResponseDto;
	}

	//Fetch Card Details for a customer
	@Override
	public FetchCardDetailsResponseDto fetchCardDetails(FetchCardDetailsRequestDto requestDto) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
