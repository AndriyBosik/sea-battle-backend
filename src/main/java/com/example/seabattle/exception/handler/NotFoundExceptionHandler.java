package com.example.seabattle.exception.handler;

import com.example.seabattle.dto.ApiErrorDto;
import com.example.seabattle.exception.NotFoundException;
import com.example.seabattle.model.api.error.NotFoundApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionHandler {
  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<ApiErrorDto> handle(NotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ApiErrorDto(new NotFoundApiError(exception.getMessage())));
  }
}
