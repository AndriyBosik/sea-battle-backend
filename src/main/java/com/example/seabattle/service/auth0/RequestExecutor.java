package com.example.seabattle.service.auth0;

import com.auth0.net.Request;

public interface RequestExecutor {
  <U, T extends Request<U>> U executeRequest(T request);
}
