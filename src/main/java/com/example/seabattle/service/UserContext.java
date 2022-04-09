package com.example.seabattle.service;

import com.example.seabattle.model.UserPrincipal;

public interface UserContext {
  String getAuthProviderUserId();

  String getUserEmail();

  Long getUserId();

  UserPrincipal getUser();
}
