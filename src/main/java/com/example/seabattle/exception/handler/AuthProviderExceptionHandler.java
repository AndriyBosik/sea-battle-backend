package com.example.seabattle.exception.handler;

import com.example.seabattle.dto.ApiErrorDto;
import com.example.seabattle.exception.AuthProviderException;
import com.example.seabattle.model.api.error.ServerApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthProviderExceptionHandler {
  @ExceptionHandler(value = AuthProviderException.class)
  public ResponseEntity<ApiErrorDto> handle() {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiErrorDto(new ServerApiError()));
  }
}
