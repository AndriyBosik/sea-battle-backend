package com.example.seabattle.service.impl;

import com.example.seabattle.model.CredentialsFakerProperties;
import com.example.seabattle.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultEmailService implements EmailService {
  private final CredentialsFakerProperties credentialsFakerProperties;

  @Override
  public String generateEmail(Long userId) {
    return credentialsFakerProperties.getEmailPrefix() + userId + "@" + credentialsFakerProperties.getEmailDomain();
  }

  @Override
  public Long parseIdFromEmail(String email) {
    int atSignIndex = email.indexOf("@");
    String strId = email.substring(credentialsFakerProperties.getEmailPrefix().length(), atSignIndex);
    return Long.parseLong(strId);
  }
}
