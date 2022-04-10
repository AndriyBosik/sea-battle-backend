package com.example.seabattle.model.api.error;

import com.example.seabattle.meta.ApiErrorType;

public class ServerApiError extends ApiError {
  @Override
  public ApiErrorType getErrorType() {
    return ApiErrorType.SERVER_ERROR;
  }

  @Override
  public String getDescription() {
    return "Internal server error. Please contact technical support!";
  }
}
