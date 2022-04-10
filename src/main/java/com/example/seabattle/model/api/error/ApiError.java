package com.example.seabattle.model.api.error;

import com.example.seabattle.meta.ApiErrorType;

public abstract class ApiError {
  public abstract ApiErrorType getErrorType();

  public abstract String getDescription();
}
