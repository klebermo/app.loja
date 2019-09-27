package org.loja.settings.idiomas;

import org.springframework.stereotype.Service;

@Service
public class IdiomasService extends org.loja.settings.Service<Idiomas> {
  public IdiomasService() {
    super(Idiomas.class);
  }
}
