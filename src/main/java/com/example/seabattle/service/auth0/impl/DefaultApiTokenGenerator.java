package com.example.seabattle.service.auth0.impl;

import com.auth0.client.auth.AuthAPI;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.Request;
import com.example.seabattle.model.AuthProperties;
import com.example.seabattle.service.auth0.ApiTokenGenerator;
import com.example.seabattle.service.auth0.RequestExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class DefaultApiTokenGenerator implements ApiTokenGenerator {
  private static final int ASSUMED_TIME_FOR_REQUEST_IN_SECONDS = 60;

  private final AuthProperties authProperties;
  private final AuthAPI auth0Api;
  private final RequestExecutor requestExecutor;

  private TokenHolder tokenHolder = null;

  @Override
  public String getToken() {
    if (tokenHolder == null || !canLiveForRequest()) {
      renewToken();
    }
    return tokenHolder.getAccessToken();
  }

  private boolean canLiveForRequest() {
    return tokenHolder.getExpiresAt().after(Date.from(Instant.now().plusSeconds(ASSUMED_TIME_FOR_REQUEST_IN_SECONDS)));
  }

  private void renewToken() {
    Request<TokenHolder> request = auth0Api.requestToken(authProperties.getManagementApiAudience());
    synchronized (this) {
      tokenHolder = requestExecutor.executeRequest(request);
    }
  }
}
