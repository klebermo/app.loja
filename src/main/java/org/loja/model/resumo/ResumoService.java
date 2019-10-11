package org.loja.model.resumo;

import org.springframework.stereotype.Service;

@Service
public class ResumoService extends org.loja.model.Service<Resumo> {
  public ResumoService() {
    super(Resumo.class);
  }
}
