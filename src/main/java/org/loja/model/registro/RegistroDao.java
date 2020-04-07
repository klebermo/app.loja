package org.loja.model.registro;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class RegistroDao extends Dao<Registro> {
  public RegistroDao() {
    super(Registro.class);
  }
}
