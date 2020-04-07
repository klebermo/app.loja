package org.loja.model.maquina;

import org.springframework.stereotype.Service;

@Service
public class MaquinaService extends org.loja.model.Service<Maquina> {
  public MaquinaService() {
    super(Maquina.class);
  }
}
