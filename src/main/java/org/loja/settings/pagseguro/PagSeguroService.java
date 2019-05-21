package org.loja.settings.pagseguro;

import org.springframework.stereotype.Service;

@Service
public class PagSeguroService extends org.loja.settings.Service<PagSeguro> {
  public PagSeguroService() {
    super(PagSeguro.class);
  }
}
