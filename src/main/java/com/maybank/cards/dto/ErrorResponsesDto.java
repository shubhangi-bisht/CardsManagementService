package com.maybank.cards.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorResponsesDto {

    private String errorCode;        // CARD_NOT_FOUND
    private String errorMessage;     // No card found for account number
    public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private Integer httpStatus;      // 404
    private LocalDateTime timestamp; // 2026-01-11T14:05:30
    private String path;             // /card/fetch-card-details

    public static ErrorResponsesDto of(
            String errorCode,
            String errorMessage,
            HttpStatus status,
            String path) {

        return new ErrorResponsesDto(
                errorCode,
                errorMessage,
                status.value(),
                LocalDateTime.now(),
                path
        );
    }

	public ErrorResponsesDto(String errorCode, String errorMessage, Integer httpStatus, LocalDateTime timestamp,
			String path) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
		this.path = path;
	}
}
