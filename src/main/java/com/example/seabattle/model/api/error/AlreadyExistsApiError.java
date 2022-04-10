package com.example.seabattle.model.api.error;

import com.example.seabattle.meta.ApiErrorType;

public class AlreadyExistsApiError extends ApiError {
  private final String entity;

  public AlreadyExistsApiError() {
    this("entity");
  }

  public AlreadyExistsApiError(String entity) {
    this.entity = entity;
  }

  @Override
  public ApiErrorType getErrorType() {
    return ApiErrorType.ALREADY_EXISTS;
  }

  @Override
  public String getDescription() {
    return String.format("%s already exists", entity);
  }
}
