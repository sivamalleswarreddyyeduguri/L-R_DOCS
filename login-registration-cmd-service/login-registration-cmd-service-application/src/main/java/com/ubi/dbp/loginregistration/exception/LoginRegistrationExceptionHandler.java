package com.ubi.dbp.loginregistration.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbp.framework.support.exception.ErrorMessage;
import dbp.framework.support.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.HttpHostConnectException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;
import reactor.core.publisher.Mono;
import com.ubi.dbp.loginregistration.exception.Error.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class LoginRegistrationExceptionHandler {
	private LoginRegistrationException loginRegistrationException;
	
	@ResponseBody
	@ExceptionHandler({LoginRegistrationException.class})
	public Mono<ResponseEntity<ErrorResponse>> handleClientResponse(LoginRegistrationException ex) {
		String uid = UUID.randomUUID().toString();
		ErrorResponse errorResponse = ErrorResponse.builder().errorId(uid)
				.errors(List.of(new ErrorMessage(ex.getCode(), ex.getMessage(), null))).build();
		return Mono.just(new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST));
	}
	@ExceptionHandler(HttpHostConnectException.class)
	public Mono<ResponseEntity<ErrorResponse>> handleHttpHostConnectException(Exception ex) {
		log.error("Unhandled exception occurred", ex);
		return buildErrorResponse(ex, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(Exception.class)
	public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
		log.error("Unhandled exception occurred", ex);
		return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
	}

	private Mono<ResponseEntity<ErrorResponse>> buildErrorResponse(Exception ex, HttpStatus status) {
		String uid = UUID.randomUUID().toString();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ErrorResponse error = objectMapper.readValue(ex.getMessage(), ErrorResponse.class);
			return Mono.just(new ResponseEntity<>(error, status));
		} catch (Exception e) {
			ErrorResponse errorResponse = ErrorResponse.builder().errorId(uid)
					.errors(List.of(new ErrorMessage("PROPER_MESSAGE_NOT_RECIEVED_FROM_SERVICE", ex.getMessage(),null))).build();
			return Mono.just(new ResponseEntity<>(errorResponse, status));
		}
	}
	

}
