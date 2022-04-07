package com.example.seabattle.service.auth0.impl;

import com.auth0.client.auth.AuthAPI;
import com.auth0.json.auth.CreatedUser;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.Request;
import com.example.seabattle.meta.Auth0Data;
import com.example.seabattle.model.AuthProperties;
import com.example.seabattle.service.auth0.AuthenticationApi;
import com.example.seabattle.service.auth0.RequestExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAuthenticationApi implements AuthenticationApi {
  private final AuthAPI auth0Api;
  private final AuthProperties authProperties;
  private final RequestExecutor requestExecutor;

  @Override
  public TokenHolder login(String email, String password) {
    Request<TokenHolder> request = auth0Api
        .login(email, password.toCharArray())
        .setAudience(authProperties.getAudience())
        .setScope(authProperties.getScope());
    return requestExecutor.executeRequest(request);
  }

  @Override
  public CreatedUser signUp(String email, String username, String password) {
    Request<CreatedUser> request = auth0Api.signUp(
        email,
        username,
        password.toCharArray(),
        Auth0Data.CONNECTION);
    return requestExecutor.executeRequest(request);
  }
}
