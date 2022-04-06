package com.example.seabattle.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Messages {
  USER_EXISTS("User with this email already exists"),
  SERVER_ERROR("Internal server error, please contact technical support");

  private final String message;
}
