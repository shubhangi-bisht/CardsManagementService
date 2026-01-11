package com.maybank.cards.weather.app.service;

import org.springframework.stereotype.Service;


import reactor.core.publisher.Mono;

@Service
public interface WeatherService {
	
	public Mono<String> fetchWeather(double lat, double lon);

}
