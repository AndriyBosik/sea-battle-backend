package com.example.seabattle.service.impl;

import com.example.seabattle.meta.Auth0Data;
import com.example.seabattle.service.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class DefaultUserContext implements UserContext {
  @Override
  public String getAuthProviderUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }

  @Override
  public String getUserNickname() {
    return getPrincipal().getClaim(Auth0Data.NICKNAME_CLAIM);
  }

  private Jwt getPrincipal() {
    return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
