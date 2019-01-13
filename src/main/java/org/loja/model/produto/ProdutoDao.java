package org.loja.model.produto;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoDao extends Dao<Produto> {
  public ProdutoDao() {
    super(Produto.class);
  }
}
