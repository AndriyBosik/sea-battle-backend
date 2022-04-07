package com.example.seabattle.service;

import com.example.seabattle.dto.*;

public interface AuthProviderService {
  TokenDto registerPasswordless(PasswordlessRegisterDto passwordlessRegisterDto);

  TokenDto updatePassword(PasswordDto passwordDto);

  TokenDto login(LoginDto loginDto);
}
