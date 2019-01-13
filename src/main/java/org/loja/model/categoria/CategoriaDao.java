package org.loja.model.categoria;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDao extends Dao<Categoria> {
  public CategoriaDao() {
    super(Categoria.class);
  }
}
