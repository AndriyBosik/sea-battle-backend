package com.example.seabattle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPrincipal {
  private Long id;
  private String nickname;
  private String email;
}
