package com.example.seabattle.service;

import com.example.seabattle.dto.*;

public interface AuthProviderService {
  TokenDto registerPasswordless(PasswordlessRegisterDto passwordlessRegisterDto);

  TokenDto updatePassword(PasswordDto passwordDto);

  TokenDto login(LoginDto loginDto);

  void updateUserNickname(NicknameDto nicknameDto);

  TokenDto refreshAccessToken(RefreshTokenDto refreshTokenDto);
}
