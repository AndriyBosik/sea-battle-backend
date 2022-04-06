package com.example.seabattle.config;

import com.auth0.client.auth.AuthAPI;
import com.example.seabattle.model.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
  private final AuthProperties authProperties;

  @Bean
  public AuthAPI auth0Api() {
    return new AuthAPI(
        authProperties.getIssuer(),
        authProperties.getClientId(),
        authProperties.getClientSecret()
    );
  }
}
