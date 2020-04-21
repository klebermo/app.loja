package org.loja.settings.mail;

import org.springframework.stereotype.Service;

@Service
public class MailService extends org.loja.settings.Service<Mail> {
  public MailService() {
    super(Mail.class);
  }
}
