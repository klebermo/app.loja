package org.loja.model.resposta;

import org.springframework.stereotype.Repository;

@Repository
public class RespostaDao extends org.loja.model.Dao<Resposta> {
  public RespostaDao() {
    super(Resposta.class);
  }
}
