package org.loja.model.titulo;

import org.springframework.stereotype.Service;

@Service
public class TituloService extends org.loja.model.Service<Titulo> {
  public TituloService() {
    super(Titulo.class);
  }
}
