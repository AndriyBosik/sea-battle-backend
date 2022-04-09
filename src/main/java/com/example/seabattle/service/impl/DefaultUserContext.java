package com.example.seabattle.service.impl;

import com.example.seabattle.mapper.UserMapper;
import com.example.seabattle.meta.Auth0Data;
import com.example.seabattle.model.UserPrincipal;
import com.example.seabattle.repository.UserRepository;
import com.example.seabattle.service.EmailService;
import com.example.seabattle.service.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class DefaultUserContext implements UserContext {
  private final EmailService emailService;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public String getAuthProviderUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }

  @Override
  public String getUserEmail() {
    return getPrincipal().getClaim(Auth0Data.EMAIL_CLAIM);
  }

  @Override
  public Long getUserId() {
    return emailService.parseIdFromEmail(getUserEmail());
  }

  @Override
  public UserPrincipal getUser() {
    return userMapper.toPrincipal(
        getUserEmail(),
        userRepository.findUserById(getUserId()));
  }

  private Jwt getPrincipal() {
    return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
