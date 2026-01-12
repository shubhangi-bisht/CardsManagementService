package com.maybank.cards.controller;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.cards.dto.CardDetailsWithTxnResDto;
import com.maybank.cards.dto.CardHolderResponseDto;
import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;
import com.maybank.cards.dto.FetchCardDetailsRequestDto;
import com.maybank.cards.dto.FetchCardDetailsResponseDto;
import com.maybank.cards.dto.UpdateCardStatusRequestDto;
import com.maybank.cards.dto.UpdateCardStatusResponseDto;
import com.maybank.cards.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    private static final Logger logger = LogManager.getLogger(CardController.class);

    //****************** CREATE CARD ********************//
    @PostMapping("/create-card")
    public ResponseEntity<CreateCardResponseDto> createCard(
            @RequestBody CreateCardRequestDto request) {

        logger.info("Create card request received for account: {}", request.getCardNumber());

        // Service layer handles exceptions
        CreateCardResponseDto response = cardService.createCard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //****************** FETCH CARD DETAILS ********************//
    @GetMapping("/fetch-card-details")
    public ResponseEntity<FetchCardDetailsResponseDto> fetchCardDetails(
            @RequestBody FetchCardDetailsRequestDto requestDto) {

        logger.info("Fetch card details request received for account: {}", requestDto.getCardNumber());


        // Service layer throws NoDataFoundException if not found
        FetchCardDetailsResponseDto response = cardService.fetchCardDetails(requestDto);
        return ResponseEntity.ok(response);
    }

    //****************** FETCH CARD HOLDERS WITH PAGINATION ********************//
    @GetMapping("/fetch-cardholder-details")
    public ResponseEntity<Page<CardHolderResponseDto>> fetchCardHolderDetails(
            @RequestParam(defaultValue = "0") int page) {

        logger.info("Fetch card holders request received, page={}", page);

        Page<CardHolderResponseDto> response = cardService.fetchCardHolderDetails(page);
        return ResponseEntity.ok(response);
    }

    //****************** UPDATE CARD STATUS ********************//
    @PutMapping("/update-card-status")
    public ResponseEntity<UpdateCardStatusResponseDto> updateCardStatus(
            @RequestBody UpdateCardStatusRequestDto request) {

        logger.info("Update card status request received for account: {}", request.getCardNumber());

        UpdateCardStatusResponseDto response = cardService.updateCardStatus(request);
        return ResponseEntity.ok(response);
    }
    
    
    @PostMapping("/transaction-details")
    public ResponseEntity<CardDetailsWithTxnResDto> getTransactionDetails(
    		@RequestBody FetchCardDetailsRequestDto requestDto) {
    	logger.info("getTransactionDetails: {}", requestDto.getCardNumber());
        return ResponseEntity.ok(cardService.getCardDetails(requestDto));
    }
}


