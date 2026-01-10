package com.maybank.cards.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class ResponseRequestLoggingUtil extends OncePerRequestFilter{

	    private static final Logger logger =
	            LogManager.getLogger(ResponseRequestLoggingUtil.class);

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain)
	            throws ServletException, IOException {

	        ContentCachingRequestWrapper requestWrapper =
	                new ContentCachingRequestWrapper(request);
	        ContentCachingResponseWrapper responseWrapper =
	                new ContentCachingResponseWrapper(response);

	        long startTime = System.currentTimeMillis();

	        filterChain.doFilter(requestWrapper, responseWrapper);

	        long timeTaken = System.currentTimeMillis() - startTime;

	        String requestBody = new String(
	                requestWrapper.getContentAsByteArray(),
	                StandardCharsets.UTF_8
	        );

	        String responseBody = new String(
	                responseWrapper.getContentAsByteArray(),
	                StandardCharsets.UTF_8
	        );

	        logger.info("""
	                REQUEST  -> Method: {} | URI: {} | Body: {}
	                RESPONSE -> Status: {} | Body: {} | TimeTaken: {} ms
	                """,
	                request.getMethod(),
	                request.getRequestURI(),
	                requestBody,
	                response.getStatus(),
	                responseBody,
	                timeTaken
	        );

	        responseWrapper.copyBodyToResponse();
	    }
	}
