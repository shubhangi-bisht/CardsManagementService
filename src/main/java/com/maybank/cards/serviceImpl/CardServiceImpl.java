package com.maybank.cards.serviceImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maybank.cards.dto.CardDetailsWithTxnResDto;
import com.maybank.cards.dto.CardHolderResponseDto;
import com.maybank.cards.dto.CardTransactionsResponseDto;
import com.maybank.cards.dto.CreateCardRequestDto;
import com.maybank.cards.dto.CreateCardResponseDto;
import com.maybank.cards.dto.FetchCardDetailsRequestDto;
import com.maybank.cards.dto.FetchCardDetailsResponseDto;
import com.maybank.cards.dto.UpdateCardStatusRequestDto;
import com.maybank.cards.dto.UpdateCardStatusResponseDto;
import com.maybank.cards.entity.CardHolder;
import com.maybank.cards.exception.DatabaseUnavailableException;
import com.maybank.cards.exception.NoDataFoundException;
import com.maybank.cards.repository.CardRepository;
import com.maybank.cards.service.CardService;
import com.maybank.cards.util.CardTransactionClient;
import com.maybank.cards.util.Mapper;
import com.maybank.cards.util.MaskingUtil;

import reactor.core.publisher.Mono;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private Mapper mapper;
    
    @Autowired
    CardTransactionClient cardTransactionClient;

    private static final Logger logger = LogManager.getLogger(CardServiceImpl.class);

    private static final int PAGE_SIZE = 10;

    //****************** CREATE CARD ********************//
    @Override
    @Transactional
    public CreateCardResponseDto createCard(CreateCardRequestDto requestDto) {
        if (requestDto == null || requestDto.getAccountNumber() == null) {
            throw new IllegalArgumentException("Request or Account number cannot be null");
        }

        logger.info("Creating card for account number: {}", requestDto.getAccountNumber());

        try {
            CardHolder card = mapper.toMap(requestDto);
            cardRepository.save(card);
        } catch (Exception ex) {
            logger.error("Error while saving card details", ex);
            throw new DatabaseUnavailableException("Unable to create card at this time", ex);
        }

        CreateCardResponseDto response = new CreateCardResponseDto();
        response.setAccountNumber(requestDto.getAccountNumber());
        response.setMessage("Account created successfully");

        return response;
    }

    //****************** FETCH CARD DETAILS ********************//
    @Override
    public FetchCardDetailsResponseDto fetchCardDetails(FetchCardDetailsRequestDto requestDto) {
        if (requestDto == null || requestDto.getCardNumber() == null) {
            throw new IllegalArgumentException("Account number cannot be null");
        }

        logger.info("Fetching card details for account number: {}", requestDto.getCardNumber());

        CardHolder cardHolder;
        try {
            cardHolder = cardRepository.findById(requestDto.getCardNumber())
                    .orElseThrow(() ->
                            new NoDataFoundException("No card found for account number "
                                    + requestDto.getCardNumber()));
        } catch (NoDataFoundException ex) {
            throw ex; // Business exception
        } catch (Exception ex) {
            logger.error("Database error while fetching card details", ex);
            throw new DatabaseUnavailableException("Unable to fetch card details", ex);
        }

        FetchCardDetailsResponseDto response = new FetchCardDetailsResponseDto();
        response.setAccountNumber(MaskingUtil.maskAccountNumber(cardHolder.getAccountNumber()));
        response.setName(cardHolder.getName());
        response.setStatus(cardHolder.getStatus());
        response.setExpiryDate(cardHolder.getExpiryDate());
        response.setMessage("Card details fetched successfully.");

        return response;
    }

    //****************** UPDATE CARD STATUS ********************//
    @Override
    @Transactional
    public UpdateCardStatusResponseDto updateCardStatus(UpdateCardStatusRequestDto requestDto) {
        if (requestDto == null || requestDto.getAccountNumber() == null) {
            throw new IllegalArgumentException("Request or Account number cannot be null");
        }

        logger.info("Updating card status for account number: {}", requestDto.getAccountNumber());

        int updatedRows;
        try {
            updatedRows = cardRepository.updateStatusByAccountNumber(
                    requestDto.getAccountNumber(),
                    requestDto.getStatus());
        } catch (Exception ex) {
            logger.error("Database error while updating card status", ex);
            throw new DatabaseUnavailableException("Unable to update card status", ex);
        }

        UpdateCardStatusResponseDto response = new UpdateCardStatusResponseDto();
        if (updatedRows > 0) {
            response.setMessage("Card status updated successfully.");
        } else {
            throw new NoDataFoundException("No card found for account number "
                    + requestDto.getAccountNumber() + ". Status update failed.");
        }

        return response;
    }

    //****************** FETCH LIST OF CARD HOLDERS (PAGINATION) ********************//
    @Override
    public Page<CardHolderResponseDto> fetchCardHolderDetails(int page) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }

        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("name").ascending());
        Page<CardHolder> cardHolders;

        try {
            cardHolders = cardRepository.findAll(pageable);
        } catch (Exception ex) {
            logger.error("Database error while fetching card holder list", ex);
            throw new DatabaseUnavailableException("Unable to fetch card holder details", ex);
        }

        List<CardHolderResponseDto> response = cardHolders.stream()
                .map(Mapper::toMapPageableRequest)
                .toList();

        return new PageImpl<>(response, pageable, cardHolders.getTotalElements());
    }
    
    
    public CardDetailsWithTxnResDto getCardDetails(FetchCardDetailsRequestDto requestDto) {
        FetchCardDetailsResponseDto cardDetails = fetchCardDetails(requestDto);

        logger.info("cardDetails: "+cardDetails);
        CardTransactionsResponseDto transactions = cardTransactionClient.fetchTransactions
        		(requestDto);

        try {
			logger.info("response 1 DTO: {}", new ObjectMapper().writeValueAsString(transactions));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        CardDetailsWithTxnResDto response = new CardDetailsWithTxnResDto();
        response.setAccountNumber(MaskingUtil.maskAccountNumber(requestDto.getCardNumber()));
        response.setCardDetails(cardDetails);
        response.setTransactions(transactions.getTransactions());

        return response;
    }
}

