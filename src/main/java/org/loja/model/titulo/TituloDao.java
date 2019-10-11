package org.loja.model.titulo;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class TituloDao extends Dao<Titulo> {
  public TituloDao() {
    super(Titulo.class);
  }
}
