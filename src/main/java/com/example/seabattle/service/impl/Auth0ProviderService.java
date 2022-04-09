package com.example.seabattle.service.impl;

import com.auth0.json.auth.TokenHolder;
import com.example.seabattle.dto.*;
import com.example.seabattle.exception.AlreadyExistsException;
import com.example.seabattle.exception.NotFoundException;
import com.example.seabattle.mapper.AuthMapper;
import com.example.seabattle.mapper.TokenMapper;
import com.example.seabattle.meta.Messages;
import com.example.seabattle.model.CredentialsFakerProperties;
import com.example.seabattle.service.AuthProviderService;
import com.example.seabattle.service.EmailService;
import com.example.seabattle.service.UserContext;
import com.example.seabattle.service.UserService;
import com.example.seabattle.service.auth0.AuthenticationApi;
import com.example.seabattle.service.auth0.ManagementApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class Auth0ProviderService implements AuthProviderService {
  private final TokenMapper tokenMapper;
  private final AuthMapper authMapper;
  private final CredentialsFakerProperties credentialsFakerProperties;
  private final UserService userService;
  private final UserContext userContext;
  private final AuthenticationApi authenticationApi;
  private final ManagementApi managementApi;
  private final EmailService emailService;

  @Transactional
  @Override
  public TokenDto registerPasswordless(PasswordlessRegisterDto passwordlessRegisterDto) {
    if (userService.findByNickname(passwordlessRegisterDto.getNickname()).isPresent()) {
      throw new AlreadyExistsException(Messages.USER_EXISTS.getMessage());
    }
    IdDto idDto = userService.createUser(authMapper.toUserDto(passwordlessRegisterDto));
    authenticationApi.signUp(
        emailService.generateEmail(idDto.getId()),
        passwordlessRegisterDto.getNickname(),
        credentialsFakerProperties.getDummyPassword());
    return login(new LoginDto(
        passwordlessRegisterDto.getNickname(),
        credentialsFakerProperties.getDummyPassword()
    ));
  }

  @Override
  public TokenDto updatePassword(PasswordDto passwordDto) {
    managementApi.updatePassword(
        userContext.getAuthProviderUserId(),
        passwordDto.getPassword());
     return login(new LoginDto(
         userContext.getUser().getNickname(),
         passwordDto.getPassword()));
  }

  @Override
  public TokenDto login(LoginDto loginDto) {
    Optional<IdDto> optionalIdDto = userService.getUserId(loginDto.getNickname());
    if (optionalIdDto.isEmpty()) {
      throw new NotFoundException("User was not found");
    }
    TokenHolder tokenHolder = authenticationApi.login(
        emailService.generateEmail(optionalIdDto.get().getId()),
        loginDto.getPassword());
    return tokenMapper.toDto(tokenHolder);
  }

  @Transactional
  @Override
  public void updateUserNickname(NicknameDto nicknameDto) {
    userService.updateNickname(nicknameDto);
    managementApi.updateNickname(
        userContext.getAuthProviderUserId(),
        nicknameDto.getNewNickname());
  }

  @Override
  public TokenDto refreshAccessToken(RefreshTokenDto refreshTokenDto) {
    TokenHolder tokenHolder = authenticationApi.refreshAccessToken(refreshTokenDto.getRefreshToken());
    return tokenMapper.toDto(tokenHolder);
  }
}
