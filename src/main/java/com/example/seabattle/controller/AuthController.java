package com.example.seabattle.controller;

import com.example.seabattle.dto.*;
import com.example.seabattle.service.AuthProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthProviderService authProviderService;

  @PostMapping("/register")
  public TokenDto register(@RequestBody PasswordlessRegisterDto passwordlessRegisterDto) {
    return authProviderService.registerPasswordless(passwordlessRegisterDto);
  }

  @PostMapping("/progress")
  public TokenDto register(@RequestBody PasswordDto passwordDto) {
    return authProviderService.updatePassword(passwordDto);
  }

  @PostMapping("/login")
  public TokenDto login(@RequestBody LoginDto loginDto) {
    return authProviderService.login(loginDto);
  }
}
