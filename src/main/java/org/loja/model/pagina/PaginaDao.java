package org.loja.model.pagina;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class PaginaDao extends Dao<Pagina> {
  public PaginaDao() {
    super(Pagina.class);
  }
}
