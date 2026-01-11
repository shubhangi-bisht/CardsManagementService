package com.maybank.cards.weatherapp.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maybank.cards.weather.app.service.WeatherService;



@RestController
public class WeatherController {

	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<String> getWeather(@RequestParam double lat,
			@RequestParam double lon) {

		String body = weatherService.fetchWeather(lat, lon);
				//.block();

		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(body);
	}
}