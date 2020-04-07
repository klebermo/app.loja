package org.loja.model.maquina;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class MaquinaDao extends Dao<Maquina> {
  public MaquinaDao() {
    super(Maquina.class);
  }
}
