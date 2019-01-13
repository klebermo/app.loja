package org.loja.model.credencial;

import org.springframework.stereotype.Service;

@Service
public class CredencialService extends org.loja.model.Service<Credencial> {
  public CredencialService() {
    super(Credencial.class);
  }
}
