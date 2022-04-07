package com.example.seabattle.service;

import com.example.seabattle.dto.*;

public interface AuthService {
  IdDto register(RegisterDto registerDto);

  IdDto registerPasswordless(PasswordlessRegisterDto passwordlessRegisterDto);

  TokenDto login(LoginDto loginDto);
}
