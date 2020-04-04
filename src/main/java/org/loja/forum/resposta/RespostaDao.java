package org.loja.forum.resposta;

import org.springframework.stereotype.Repository;

@Repository
public class RespostaDao extends org.loja.forum.Dao<Resposta> {
  public RespostaDao() {
    super(Resposta.class);
  }
}
