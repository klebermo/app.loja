package org.loja.settings.geral;

import org.springframework.stereotype.Service;

@Service
public class GeralService extends org.loja.settings.Service<Geral> {
  public GeralService() {
    super(Geral.class);
  }
}
