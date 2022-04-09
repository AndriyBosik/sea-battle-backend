package com.example.seabattle.service.auth0;

public interface ManagementApi {
  void updatePassword(String userId, String newPassword);

  void updateNickname(String userId, String nickname);
}
