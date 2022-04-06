package com.example.seabattle.service;

import com.example.seabattle.dto.IdDto;
import com.example.seabattle.dto.PasswordlessRegisterDto;
import com.example.seabattle.dto.RegisterDto;

public interface AuthService {
  IdDto register(RegisterDto registerDto);

  IdDto registerPasswordless(PasswordlessRegisterDto passwordlessRegisterDto);
}
