package com.example.seabattle.service.auth0;

public interface ManagementApi {
  void changePassword(String userId, String newPassword);
}