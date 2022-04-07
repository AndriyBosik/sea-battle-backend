package com.example.seabattle.service.impl;

import com.auth0.json.auth.TokenHolder;
import com.example.seabattle.dto.*;
import com.example.seabattle.exception.AlreadyExistsException;
import com.example.seabattle.mapper.AuthMapper;
import com.example.seabattle.mapper.TokenMapper;
import com.example.seabattle.meta.Messages;
import com.example.seabattle.model.AuthProperties;
import com.example.seabattle.service.AuthProviderService;
import com.example.seabattle.service.UserContext;
import com.example.seabattle.service.UserService;
import com.example.seabattle.service.auth0.AuthenticationApi;
import com.example.seabattle.service.auth0.ManagementApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class Auth0ProviderService implements AuthProviderService {
  private final TokenMapper tokenMapper;
  private final AuthMapper authMapper;
  private final AuthProperties authProperties;
  private final UserService userService;
  private final UserContext userContext;
  private final AuthenticationApi authenticationApi;
  private final ManagementApi managementApi;

  @Transactional
  @Override
  public TokenDto registerPasswordless(PasswordlessRegisterDto passwordlessRegisterDto) {
    if (userService.findByNickname(passwordlessRegisterDto.getNickname()).isPresent()) {
      throw new AlreadyExistsException(Messages.USER_EXISTS.getMessage());
    }
    userService.createUser(authMapper.toUserDto(passwordlessRegisterDto));
    authenticationApi.signUp(
        toEmail(passwordlessRegisterDto.getNickname()),
        passwordlessRegisterDto.getNickname(),
        authProperties.getDummyPassword());
    return login(new LoginDto(
        passwordlessRegisterDto.getNickname(),
        authProperties.getDummyPassword()
    ));
  }

  @Override
  public TokenDto updatePassword(PasswordDto passwordDto) {
    managementApi.changePassword(
        userContext.getAuthProviderUserId(),
        passwordDto.getPassword());
     return login(new LoginDto(
         userContext.getUserNickname(),
         passwordDto.getPassword()));
  }

  @Override
  public TokenDto login(LoginDto loginDto) {
    TokenHolder tokenHolder = authenticationApi.login(
        toEmail(loginDto.getNickname()),
        loginDto.getPassword());
    return tokenMapper.toDto(tokenHolder);
  }

  private String toEmail(String nickname) {
    return nickname + "@" + authProperties.getDefaultEmailDomain();
  }
}
