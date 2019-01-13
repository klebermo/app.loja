package org.loja.model.cesta;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class CestaDao extends Dao<Cesta> {
  public CestaDao() {
    super(Cesta.class);
  }
}
