package com.example.seabattle.service;

import com.example.seabattle.dto.IdDto;
import com.example.seabattle.dto.LoginDto;
import com.example.seabattle.dto.RegisterDto;
import com.example.seabattle.dto.TokenDto;

public interface AuthProviderService {
  IdDto register(RegisterDto registerDto);

  TokenDto login(LoginDto loginDto);
}
