package com.maybank.cards.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;
import com.maybank.cards.dto.FetchCardDetailsRequestDto;
import com.maybank.cards.dto.FetchCardDetailsResponseDto;
import com.maybank.cards.dto.UpdateCardStatusRequestDto;
import com.maybank.cards.dto.UpdateCardStatusResponseDto;
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

	//Create Card for a new user
	@PostMapping(value = "/create-card")
	public CreateCardResponseDto createCard(@RequestBody CreateCardRequestDto createCardRequest){
		CreateCardResponseDto createCardResponseDto = new CreateCardResponseDto();
		try{
			createCardResponseDto = cardService.createCard(createCardRequest);

		} catch (java.lang.Exception e) {
			createCardResponseDto.setMessage("Some exception occured while creating account. "+e);
		}
		return createCardResponseDto;
	}

	//Fetch Card Details for a customer
	@GetMapping(value = "/fetch-card-details")
	public FetchCardDetailsResponseDto fetchCardDetails(@RequestBody FetchCardDetailsRequestDto fetchCardDetailsRequestDto) {
		FetchCardDetailsResponseDto fetchCardDetailsResponseDto = new FetchCardDetailsResponseDto();
		try{
			System.out.println(fetchCardDetailsRequestDto.getAccountNumber());
			fetchCardDetailsResponseDto = cardService.fetchCardDetails(fetchCardDetailsRequestDto);

		} catch (java.lang.Exception e) {
			fetchCardDetailsResponseDto.setMessage("Some exception occured while fetch data. "+e);
		}
		return fetchCardDetailsResponseDto;
	}
	
	//Update status of the card
	@PutMapping(value = "/update-card-status")
	public UpdateCardStatusResponseDto updateCardStatus(@RequestBody UpdateCardStatusRequestDto requestDto) {
		UpdateCardStatusResponseDto updateCardStatusResponseDto = new UpdateCardStatusResponseDto();
		try {
			if(Objects.nonNull(requestDto.getAccountNumber()) 
					&& Objects.nonNull(requestDto.getStatus())){
				updateCardStatusResponseDto = cardService.updateCardStatus(requestDto);
			}
			else {
				updateCardStatusResponseDto.setMessage("No Account Number found in request");
			}
		}
		catch(Exception e) {
			updateCardStatusResponseDto.setMessage("Some exception occured while fetch data. "+e);
		}
		return updateCardStatusResponseDto;
	}
}

