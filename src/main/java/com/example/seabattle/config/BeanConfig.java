package com.example.seabattle.config;

import com.auth0.client.auth.AuthAPI;
import com.example.seabattle.model.AuthProperties;
import com.example.seabattle.security.AudienceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
  private final AuthProperties authProperties;

  @Bean
  public AuthAPI authApi() {
    return new AuthAPI(
        authProperties.getIssuer(),
        authProperties.getClientId(),
        authProperties.getClientSecret()
    );
  }

  @Bean
  public JwtDecoder jwtDecoder(AudienceValidator audienceValidator) {
    NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(authProperties.getIssuer());

    jwtDecoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(
        JwtValidators.createDefaultWithIssuer(authProperties.getIssuer()),
        audienceValidator));

    return jwtDecoder;
  }
}
