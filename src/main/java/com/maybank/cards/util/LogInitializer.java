package com.maybank.cards.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class LogInitializer {

	@PostConstruct
	public void createLogDir() {
		System.out.println("user dir: "+System.getProperty("user.dir"));
		Path logDir = Paths.get(System.getProperty("user.dir"), "logs");
		System.out.println("Path: "+logDir);
		try {
			Files.createDirectories(logDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
