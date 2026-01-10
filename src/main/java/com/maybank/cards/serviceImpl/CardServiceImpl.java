package com.maybank.cards.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;
import com.maybank.cards.dto.FetchCardDetailsRequestDto;
import com.maybank.cards.dto.FetchCardDetailsResponseDto;
import com.maybank.cards.dto.UpdateCardStatusRequestDto;
import com.maybank.cards.dto.UpdateCardStatusResponseDto;
import com.maybank.cards.entity.CardHolder;
import com.maybank.cards.repository.CardRepository;
import com.maybank.cards.service.CardService;
import com.maybank.cards.util.Mapper;

@Service
public class CardServiceImpl implements CardService{

	@Autowired
	CardRepository cardRepository;
	
	
	@Autowired
	Mapper mapper;
	
	private CardHolder cardHolder = new CardHolder();
	
	//Create Card for a customer
	@Override
	@Transactional
	public CreateCardResponseDto createCard(CreateCardRequestDto requestDto) {
		CreateCardResponseDto createCardResponseDto = new CreateCardResponseDto();
		cardRepository.save(mapper.toMap(requestDto));
		createCardResponseDto.setMessage("Account created successfully.");
		createCardResponseDto.setAccountNumber(requestDto.getAccountNumber());
		return createCardResponseDto;
	}

	//Fetch Card Details for a customer
	@Override
	public FetchCardDetailsResponseDto fetchCardDetails(FetchCardDetailsRequestDto requestDto) {
		FetchCardDetailsResponseDto fetchCardDetailsResponseDto = new FetchCardDetailsResponseDto();

		System.out.println("account number: "+requestDto.getAccountNumber());

		if(Objects.nonNull(requestDto.getAccountNumber())) {
			cardHolder = cardRepository.findById(requestDto.getAccountNumber()).orElse(null);
			if(Objects.nonNull(cardHolder)) {
				fetchCardDetailsResponseDto.setAccountNumber(cardHolder.getAccountNumber());
				fetchCardDetailsResponseDto.setName(cardHolder.getName());
				fetchCardDetailsResponseDto.setStatus(cardHolder.getStatus());
				fetchCardDetailsResponseDto.setExpiryDate(cardHolder.getExpiryDate());
				fetchCardDetailsResponseDto.setMessage("Card details fetched successfully.");
				return fetchCardDetailsResponseDto;
			}
			fetchCardDetailsResponseDto.setMessage("No detail found for entered Account Number.");
		}
		fetchCardDetailsResponseDto.setMessage("Account Number cannot be blank.");

		return fetchCardDetailsResponseDto;
	}

	@Override
	@Transactional
	public UpdateCardStatusResponseDto updateCardStatus(UpdateCardStatusRequestDto requestDto) {
		UpdateCardStatusResponseDto updateCardStatusResponseDto = new UpdateCardStatusResponseDto();
		
		int updateStatus = cardRepository.updateStatusByAccountNumber(requestDto.getAccountNumber()
				, requestDto.getStatus());
		if(updateStatus>0) {
			updateCardStatusResponseDto.setMessage("Card status updated successfully.");
			return updateCardStatusResponseDto;
		}
		else {
			updateCardStatusResponseDto.setMessage("Some error occured while updating Card"
					+ " status for Account Number "+requestDto.getAccountNumber()+".");
		}
		return updateCardStatusResponseDto;
	}
	


}
