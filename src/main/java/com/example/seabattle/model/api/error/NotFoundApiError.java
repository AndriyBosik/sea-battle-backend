package com.example.seabattle.model.api.error;

import com.example.seabattle.meta.ApiErrorType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFoundApiError extends ApiError {
  private final String message;

  @Override
  public ApiErrorType getErrorType() {
    return ApiErrorType.NOT_FOUND;
  }

  @Override
  public String getDescription() {
    return message;
  }
}
