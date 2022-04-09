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
@ConfigurationProperties(prefix = "credentials-faker")
public class CredentialsFakerProperties {
  private String emailPrefix;
  private String emailDomain;
  private String dummyPassword;
}
