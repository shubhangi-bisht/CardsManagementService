package com.maybank.cards.weather.app.serviceimpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.maybank.cards.weather.app.service.WeatherService;

import reactor.core.publisher.Mono;
@Service
public class WeatherServiceImpl implements WeatherService{

	private final WebClient webClient;

	@Value("${weather-service-base-url}")
	private String url;
	
	public WeatherServiceImpl(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("https://api.open-meteo.com").build();
	}

	public Mono<String> fetchWeather(double lat, double lon) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/v1/forecast")
						.queryParam("latitude", lat)
						.queryParam("longitude", lon)
						.queryParam("hourly", "temperature_2m")
						.build())
				.retrieve()
				.bodyToMono(String.class);
	}

}

