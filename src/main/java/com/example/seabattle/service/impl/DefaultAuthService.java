package com.example.seabattle.service.impl;

import com.example.seabattle.dto.*;
import com.example.seabattle.mapper.UserMapper;
import com.example.seabattle.service.AuthProviderService;
import com.example.seabattle.service.AuthService;
import com.example.seabattle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {
  private final AuthProviderService authProviderService;
  private final UserService userService;
  private final UserMapper userMapper;

  @Override
  public IdDto register(RegisterDto registerDto) {
    return authProviderService.register(registerDto);
  }

  @Override
  public IdDto registerPasswordless(PasswordlessRegisterDto passwordlessRegisterDto) {
    return userService.createUser(userMapper.toUser(passwordlessRegisterDto));
  }

  @Override
  public TokenDto login(LoginDto loginDto) {
    return authProviderService.login(loginDto);
  }
}
