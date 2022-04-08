package com.example.seabattle.service.auth0;

import com.auth0.json.auth.CreatedUser;
import com.auth0.json.auth.TokenHolder;

public interface AuthenticationApi {
  TokenHolder login(String email, String password);

  CreatedUser signUp(String email, String username, String password);

  TokenHolder refreshAccessToken(String refreshToken);
}
