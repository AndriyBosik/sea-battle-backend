package com.example.seabattle.service.auth0.impl;

import com.auth0.json.mgmt.users.User;
import com.auth0.net.Request;
import com.example.seabattle.service.auth0.Auth0ManagementApi;
import com.example.seabattle.service.auth0.ManagementApi;
import com.example.seabattle.service.auth0.RequestExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultManagementApi implements ManagementApi {
  private final RequestExecutor requestExecutor;
  private final Auth0ManagementApi managementApi;

  @Override
  public void updatePassword(String userId, String newPassword) {
    User newUser = new User();
    newUser.setPassword(newPassword.toCharArray());
    updateUser(userId, newUser);
  }

  @Override
  public void updateNickname(String userId, String nickname) {
    User newUser = new User();
    newUser.setNickname(nickname);
    updateUser(userId, newUser);
  }

  private void updateUser(String userId, User newUser) {
    Request<User> request = managementApi.get().users().get(userId, null);
    User user = requestExecutor.executeRequest(request);
    if (user == null) {
      return;
    }
    Request<User> updateUserRequest = managementApi.get().users().update(user.getId(), newUser);
    requestExecutor.executeRequest(updateUserRequest);
  }
}
