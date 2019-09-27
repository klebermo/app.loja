package org.loja.settings.email;

import org.springframework.stereotype.Repository;

@Repository
public class EmailDao extends org.loja.settings.Dao<Email> {
  public EmailDao() {
    super(Email.class);
  }
}
