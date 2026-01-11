package com.maybank.cards.weather.app.service;

import org.springframework.stereotype.Service;


import reactor.core.publisher.Mono;

@Service
public interface WeatherService {
	
	public String fetchWeather(double lat, double lon);

}
