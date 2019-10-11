package org.loja.model.texto;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class TextoDao extends Dao<Texto> {
  public TextoDao() {
    super(Texto.class);
  }
}
