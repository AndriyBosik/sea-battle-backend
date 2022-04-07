package com.example.seabattle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {
  private String issuer;
  private String scope;
  private String audience;
  private String clientId;
  private String clientSecret;
}
