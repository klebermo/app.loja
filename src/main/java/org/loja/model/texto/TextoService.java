package org.loja.model.texto;

import org.springframework.stereotype.Service;

@Service
public class TextoService extends org.loja.model.Service<Texto> {
  public TextoService() {
    super(Texto.class);
  }
}
