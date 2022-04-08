package com.example.seabattle.service.auth0.impl;

import com.auth0.client.mgmt.ManagementAPI;
import com.example.seabattle.model.AuthProperties;
import com.example.seabattle.service.auth0.ApiTokenGenerator;
import com.example.seabattle.service.auth0.Auth0ManagementApi;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class DefaultAuth0ManagementApi implements Auth0ManagementApi {
  private final ApiTokenGenerator apiTokenGenerator;
  private final ManagementAPI managementAPI;

  public DefaultAuth0ManagementApi(AuthProperties authProperties, ApiTokenGenerator apiTokenGenerator) {
    this.apiTokenGenerator = apiTokenGenerator;
    managementAPI = new ManagementAPI(authProperties.getIssuer(), "");
  }

  @Override
  public ManagementAPI get() {
    managementAPI.setApiToken(apiTokenGenerator.getToken());
    return managementAPI;
  }
}
