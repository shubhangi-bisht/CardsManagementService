package com.maybank.cards.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.maybank.cards.dto.CardTransactionsResponseDto;
import com.maybank.cards.dto.FetchCardDetailsRequestDto;
import com.maybank.cards.serviceImpl.CardServiceImpl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CardTransactionClient {


	@Value("${fetch-card-transactions}")
	private String url;

	@Value("${transaction-service-base-url}")
	private String transactionServiceBaseUrl;
	
	 private static final Logger logger = LogManager.getLogger(CardServiceImpl.class);

	public CardTransactionsResponseDto fetchTransactions(FetchCardDetailsRequestDto requestDto) {

		logger.info("transactionServiceBaseUrl: "+transactionServiceBaseUrl );
		logger.info("url: "+url );
		
		logger.info("complete url: "+transactionServiceBaseUrl+url );
		try {
			logger.info("Request DTO: {}", new ObjectMapper().writeValueAsString(requestDto));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 WebClient webClient = WebClient.builder().baseUrl(transactionServiceBaseUrl).build();
		
		return webClient.post()
		        .uri(url)
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(requestDto)
		        .retrieve()
		        .bodyToFlux(CardTransactionsResponseDto.class)
		        .blockLast();
		  
	}
}


