package org.loja.settings.email;

import org.springframework.stereotype.Service;

@Service
public class EmailService extends org.loja.settings.Service<Email> {
  public EmailService() {
    super(Email.class);
  }
}
