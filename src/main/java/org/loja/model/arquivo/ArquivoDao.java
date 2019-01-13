package org.loja.model.arquivo;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class ArquivoDao extends Dao<Arquivo> {
  public ArquivoDao() {
    super(Arquivo.class);
  }
}
