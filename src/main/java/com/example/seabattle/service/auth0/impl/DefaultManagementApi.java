package com.example.seabattle.service.auth0.impl;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.Request;
import com.example.seabattle.model.AuthProperties;
import com.example.seabattle.service.auth0.ManagementApi;
import com.example.seabattle.service.auth0.RequestExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultManagementApi implements ManagementApi {
  private final AuthAPI auth0Api;
  private final AuthProperties authProperties;
  private final RequestExecutor requestExecutor;

  @Override
  public void changePassword(String userId, String newPassword) {
    ManagementAPI mgmtApi = createManagementApi();
    Request<User> request = mgmtApi.users().get(userId, null);
    User user = requestExecutor.executeRequest(request);
    changeUserPassword(mgmtApi, user, newPassword);
  }

  private void changeUserPassword(ManagementAPI mgmtApi, User user, String newPassword) {
    if (user == null) {
      return;
    }
    User newUser = new User();
    newUser.setPassword(newPassword.toCharArray());
    Request<User> updateUserRequest = mgmtApi.users().update(user.getId(), newUser);
    requestExecutor.executeRequest(updateUserRequest);
  }

  private ManagementAPI createManagementApi() {
    Request<TokenHolder> request = auth0Api.requestToken(authProperties.getManagementApiAudience());
    TokenHolder tokenHolder = requestExecutor.executeRequest(request);
    return new ManagementAPI(authProperties.getIssuer(), tokenHolder.getAccessToken());
  }
}
