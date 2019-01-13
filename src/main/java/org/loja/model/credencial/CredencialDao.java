package org.loja.model.credencial;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class CredencialDao extends Dao<Credencial> {
  public CredencialDao() {
    super(Credencial.class);
  }
}
