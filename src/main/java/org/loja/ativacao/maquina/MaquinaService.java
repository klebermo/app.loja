package org.loja.ativacao.maquina;

import org.springframework.stereotype.Service;

@Service
public class MaquinaService extends org.loja.ativacao.Service<Maquina> {
  public MaquinaService() {
    super(Maquina.class);
  }
}
