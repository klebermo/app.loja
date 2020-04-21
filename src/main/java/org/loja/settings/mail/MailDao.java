package org.loja.settings.mail;

import org.springframework.stereotype.Repository;

@Repository
public class MailDao extends org.loja.settings.Dao<Mail> {
  public MailDao() {
    super(Mail.class);
  }
}
