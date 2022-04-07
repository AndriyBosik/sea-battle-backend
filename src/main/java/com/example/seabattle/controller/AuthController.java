package com.example.seabattle.controller;

import com.example.seabattle.dto.*;
import com.example.seabattle.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register/passwordless")
  public IdDto registerPasswordlessUser(@RequestBody PasswordlessRegisterDto passwordlessRegisterDto) {
    return authService.registerPasswordless(passwordlessRegisterDto);
  }

  @PostMapping("/register")
  public IdDto register(@RequestBody RegisterDto registerDto) {
    return authService.register(registerDto);
  }

  @PostMapping("/login")
  public TokenDto login(@RequestBody LoginDto loginDto) {
    return authService.login(loginDto);
  }
}
