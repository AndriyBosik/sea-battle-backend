package com.example.seabattle.exception;

public class AuthProviderException extends RuntimeException {
  public AuthProviderException(String message) {
    super(message);
  }

  public AuthProviderException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
