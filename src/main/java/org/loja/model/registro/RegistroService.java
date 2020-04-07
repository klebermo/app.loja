package org.loja.model.registro;

import org.springframework.stereotype.Service;

@Service
public class RegistroService extends org.loja.model.Service<Registro> {
  public RegistroService() {
    super(Registro.class);
  }
}
