package org.loja.model.autorizacao;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class AutorizacaoDao extends Dao<Autorizacao> {
  public AutorizacaoDao() {
    super(Autorizacao.class);
  }
}
