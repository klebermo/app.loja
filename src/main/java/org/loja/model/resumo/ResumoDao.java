package org.loja.model.resumo;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class ResumoDao extends Dao<Resumo> {
  public ResumoDao() {
    super(Resumo.class);
  }
}
