package org.loja.ativacao.registro;

import org.springframework.stereotype.Service;

@Service
public class RegistroService extends org.loja.ativacao.Service<Registro> {
  public RegistroService() {
    super(Registro.class);
  }
}
