package org.loja.forum.resposta;

import org.springframework.stereotype.Service;

@Service
public class RespostaService extends org.loja.forum.Service<Resposta> {
  public RespostaService() {
    super(Resposta.class);
  }
}
