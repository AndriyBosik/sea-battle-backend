package com.example.seabattle.exception.handler;

import com.example.seabattle.dto.ApiErrorDto;
import com.example.seabattle.exception.AlreadyExistsException;
import com.example.seabattle.model.api.error.AlreadyExistsApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AlreadyExistsExceptionHandler {
  @ExceptionHandler(value = AlreadyExistsException.class)
  public ResponseEntity<ApiErrorDto> handle(AlreadyExistsException exception) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ApiErrorDto(
            new AlreadyExistsApiError()
        ));
  }
}
