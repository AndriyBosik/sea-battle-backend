package com.example.seabattle.service.auth0.impl;

import com.auth0.exception.Auth0Exception;
import com.auth0.net.Request;
import com.example.seabattle.exception.AuthProviderException;
import com.example.seabattle.meta.Messages;
import com.example.seabattle.service.auth0.RequestExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultRequestExecutor implements RequestExecutor {
  private static final String AUTH0_EXCEPTION_MESSAGE = "Auth0 exception";

  @Override
  public <U, T extends Request<U>> U executeRequest(T request) {
    try {
      return request.execute();
    } catch (Auth0Exception exception) {
      log.error(AUTH0_EXCEPTION_MESSAGE, exception);
      throw new AuthProviderException(Messages.SERVER_ERROR.getMessage(), exception);
    }
  }
}
