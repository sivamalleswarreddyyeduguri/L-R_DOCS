package com.ubi.dbp.loginregistration.bff.controller;

import java.time.LocalDateTime;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ServerWebInputException;

import com.ubi.dbp.loginregistration.bff.exception.LoginRegistrationException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import jakarta.validation.ValidationException;
import dbp.framework.proxy.common.client.DbpHttpClientException;
import dbp.framework.support.exception.ErrorMessage;
import dbp.framework.support.exception.ErrorResponse;
  
@Slf4j
@ControllerAdvice
public class LoginRegistrationBffGlobalExceptionHandler {
	// handling query service exception
	@ExceptionHandler(DbpHttpClientException.class)
	public Mono<ResponseEntity<String>> handleDbpHttpClientException(DbpHttpClientException ex) {
		log.info("error message from service {} ,{} ", ex.getMessage(), ex.getHttpCode());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return Mono.just(new ResponseEntity<String>(ex.getMessage(), headers, HttpStatus.resolve(ex.getHttpCode())));
	}

	// for no request body
	@ExceptionHandler(MethodNotAllowedException.class)
	public Mono<ResponseEntity<ErrorResponse>> handleMethodNotSupportedException(MethodNotAllowedException ex) {
		log.error("MethodNotAllowedException exception occurred.....", ex);
		String uid = UUID.randomUUID().toString();
		ErrorResponse error = ErrorResponse.builder().errorId(uid).errorAt(LocalDateTime.now())
				.errors(Collections.singletonList(ErrorMessage.builder().code(HttpStatus.METHOD_NOT_ALLOWED.name())
						.message("Method Not Allowed").build()))
				.build();
		return Mono.just(new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED));
	}

	// for no request body
	@ExceptionHandler(ServerWebInputException.class)
	public Mono<ResponseEntity<ErrorResponse>> handleEmptyBodyException(ServerWebInputException ex) {
		log.error("ServerWebInputException exception occurred.....", ex);
		String uid = UUID.randomUUID().toString();
		ErrorResponse error = ErrorResponse.builder().errorId(uid).errorAt(LocalDateTime.now()).errors(Collections
				.singletonList(ErrorMessage.builder().code("400").message("Failed to read HTTP message").build()))
				.build();
		return Mono.just(new ResponseEntity<>(error, HttpStatus.BAD_REQUEST));
	}

	// called when any input field is blank
	@ExceptionHandler(LoginRegistrationException.class)
	public Mono<ResponseEntity<ErrorResponse>> handleInvalidBodyException(LoginRegistrationException ex) {
		log.error("LoginRegistration exception occurred.....", ex);
		String uid = UUID.randomUUID().toString();
		ErrorResponse error = ErrorResponse.builder().errorId(uid).errorAt(LocalDateTime.now())
				.errors(Collections
						.singletonList(ErrorMessage.builder().code(ex.getCode()).message(ex.getMessage()).build()))
				.build();
		return Mono.just(new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR));

	}

	// called when any field is missing from payload
	@ExceptionHandler(ValidationException.class)
	public Mono<ResponseEntity<ErrorResponse>> handleRequestException(ValidationException ex) {
		log.error("ValidationException exception occurred.....", ex);
		String uid = UUID.randomUUID().toString();
		ErrorResponse errorResponse;
		ErrorMessage error = ErrorMessage.builder().code(HttpStatus.BAD_REQUEST.name()).message(ex.getMessage())
				.build();
		errorResponse = ErrorResponse.builder().errorId(uid).errorAt(LocalDateTime.now()).errors(List.of(error))
				.build();
		return Mono.just(new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public Mono<ResponseEntity<ErrorResponse>> handleIllegalArgumentException(IllegalArgumentException ex) {
		log.error("IllegalArgumentException occurred.....", ex);
		String uid = UUID.randomUUID().toString();
		ErrorResponse error = ErrorResponse.builder().errorId(uid).errorAt(LocalDateTime.now())
				.errors(Collections.singletonList(ErrorMessage.builder().code("400").message(ex.getMessage()).build()))
				.build();
		return Mono.just(new ResponseEntity<>(error, HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(Exception.class)
	public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
		log.error("Exception occurred.....", ex);
		String uid = UUID.randomUUID().toString();

		ErrorResponse error = ErrorResponse.builder().errorId(uid).errorAt(LocalDateTime.now())
				.errors(Collections.singletonList(ErrorMessage.builder().code("500").message(ex.getMessage()).build()))
				.build();
		return Mono.just(new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@ExceptionHandler(WebExchangeBindException.class)
	public Mono<ResponseEntity<ErrorResponse>> handleWebExchangeBindException(WebExchangeBindException ex){

		    log.error("WebExchangeBindException exception occurred.......", ex);
			String uid = UUID.randomUUID().toString();

			List<ErrorMessage> errorMessages = ex.getBindingResult().getFieldErrors().stream()
					.map(error -> ErrorMessage.builder()
							.code("400")
							.message(String.format("%s: %s",error.getField(), error.getDefaultMessage()))
							.build())
					.collect(Collectors.toList());

			ErrorResponse errorResponse = ErrorResponse.builder()
					.errorId(uid)
					.errorAt(LocalDateTime.now())
					.errors(errorMessages)
					.build();

			return Mono.just(new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST));

	}
}



