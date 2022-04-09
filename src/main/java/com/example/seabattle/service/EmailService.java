package com.example.seabattle.service;

public interface EmailService {
  String generateEmail(Long userId);

  Long parseIdFromEmail(String email);
}
