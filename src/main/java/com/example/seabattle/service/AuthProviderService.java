package com.example.seabattle.service;

import com.example.seabattle.dto.IdDto;
import com.example.seabattle.dto.RegisterDto;

public interface AuthProviderService {
  IdDto register(RegisterDto registerDto);
}
