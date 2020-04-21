package org.loja.model.recovery_password;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class RecoveryPasswordDao extends Dao<RecoveryPassword> {
  public RecoveryPasswordDao() {
    super(RecoveryPassword.class);
  }
}
