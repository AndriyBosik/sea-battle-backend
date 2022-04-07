package com.example.seabattle.service.impl;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.Request;
import com.example.seabattle.dto.IdDto;
import com.example.seabattle.dto.LoginDto;
import com.example.seabattle.dto.RegisterDto;
import com.example.seabattle.dto.TokenDto;
import com.example.seabattle.exception.AlreadyExistsException;
import com.example.seabattle.exception.AuthProviderException;
import com.example.seabattle.mapper.TokenMapper;
import com.example.seabattle.mapper.UserMapper;
import com.example.seabattle.meta.Messages;
import com.example.seabattle.model.AuthProperties;
import com.example.seabattle.service.AuthProviderService;
import com.example.seabattle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class Auth0ProviderService implements AuthProviderService {
  private static final String CONNECTION = "Username-Password-Authentication";
  private static final String AUTH0_EXCEPTION_MESSAGE = "Auth0 exception";

  private final UserService userService;
  private final UserMapper userMapper;
  private final TokenMapper tokenMapper;
  private final AuthAPI auth0Api;
  private final AuthProperties authProperties;

  @Transactional
  @Override
  public IdDto register(RegisterDto registerDto) {
    if (userService.findByNickname(registerDto.getNickname()).isPresent()) {
      throw new AlreadyExistsException(Messages.USER_EXISTS.getMessage());
    }
    try {
      IdDto idDto = userService.createUser(userMapper.toUser(registerDto));
      auth0Api.signUp(
          registerDto.getNickname() + "@fake.email",
          registerDto.getNickname(),
          registerDto.getPassword().toCharArray(),
          CONNECTION).execute();
      return idDto;
    } catch (Auth0Exception exception) {
      log.error(AUTH0_EXCEPTION_MESSAGE, exception);
      throw new AuthProviderException(Messages.SERVER_ERROR.getMessage(), exception);
    }
  }

  @Override
  public TokenDto login(LoginDto loginDto) {
    Request<TokenHolder> request = auth0Api
        .login(loginDto.getNickname() + "@fake.email", loginDto.getPassword().toCharArray())
        .setAudience(authProperties.getAudience())
        .setScope(authProperties.getScope());
    return tokenMapper.toDto(executeRequest(request));
  }

  private <U, T extends Request<U>> U executeRequest(T request) {
    try {
      return request.execute();
    } catch (Auth0Exception exception) {
      log.error(AUTH0_EXCEPTION_MESSAGE, exception);
      throw new AuthProviderException(Messages.SERVER_ERROR.getMessage(), exception);
    }
  }
}
