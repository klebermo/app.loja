package org.loja.model.cesta;

import org.springframework.stereotype.Service;

@Service
public class CestaService extends org.loja.model.Service<Cesta> {
  public CestaService() {
    super(Cesta.class);
  }
}
