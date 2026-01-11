package com.maybank.cards.exception;

import java.sql.SQLTimeoutException;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.maybank.cards.dto.ErrorResponsesDto;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ================= NO DATA FOUND (BUSINESS) ================= */
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorResponsesDto> handleNoDataFound(
            NoDataFoundException ex,
            HttpServletRequest request) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponsesDto.of(
                        "CARD_NOT_FOUND",
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND,
                        request.getRequestURI()
                ));
    }

    /* ================= INVALID REQUEST ================= */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponsesDto> handleBadRequest(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponsesDto.of(
                        "INVALID_REQUEST",
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        request.getRequestURI()
                ));
    }

    /* ================= DATABASE DOWN ================= */
    @ExceptionHandler({
        DataAccessResourceFailureException.class,
        JDBCConnectionException.class
    })
    public ResponseEntity<ErrorResponsesDto> handleDatabaseDown(
            Exception ex,
            HttpServletRequest request) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ErrorResponsesDto.of(
                        "DATABASE_UNAVAILABLE",
                        "Database service is currently unavailable. Please try later.",
                        HttpStatus.SERVICE_UNAVAILABLE,
                        request.getRequestURI()
                ));
    }

    /* ================= DATABASE TIMEOUT ================= */
    @ExceptionHandler(SQLTimeoutException.class)
    public ResponseEntity<ErrorResponsesDto> handleTimeout(
            SQLTimeoutException ex,
            HttpServletRequest request) {

        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body(ErrorResponsesDto.of(
                        "DATABASE_TIMEOUT",
                        "Request timed out while processing.",
                        HttpStatus.GATEWAY_TIMEOUT,
                        request.getRequestURI()
                ));
    }

    /* ================= SYSTEM ERROR ================= */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponsesDto> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponsesDto.of(
                        "INTERNAL_SERVER_ERROR",
                        "Internal server error. Please try after sometime.",
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        request.getRequestURI()
                ));
    }
}
