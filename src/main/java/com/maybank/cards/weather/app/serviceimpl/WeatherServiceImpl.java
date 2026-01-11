package com.maybank.cards.weather.app.serviceimpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.maybank.cards.weather.app.service.WeatherService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import reactor.core.publisher.Mono;
import java.time.Duration;
@Service
public class WeatherServiceImpl implements WeatherService{

	private final WebClient webClient;

	@Value("${weather-service-base-url}")
	private String url;
	
	private static final String WEATHER_CB = "weatherService";
	
	public WeatherServiceImpl(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("https://api.open-meteo.com").build();
	}

    @Retry(name = WEATHER_CB)
    @CircuitBreaker(name = WEATHER_CB, fallbackMethod = "fallbackWeather")
    public String fetchWeather(double lat, double lon) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast")
                        .queryParam("latitude", lat)
                        .queryParam("longitude", lon)
                        .queryParam("hourly", "temperature_2m")
                        .build())
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        resp -> Mono.error(new RuntimeException("Weather API failed: " + resp.statusCode())))
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(2)); // blocking call
    }

    public String fallbackWeather(double lat, double lon, Throwable ex) {
        System.out.println("Fallback triggered: " + ex.getMessage());
        return "{\"error\":\"Weather API unavailable\"}";
    }

}

