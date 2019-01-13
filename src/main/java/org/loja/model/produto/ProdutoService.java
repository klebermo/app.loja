package org.loja.model.produto;

import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends org.loja.model.Service<Produto> {
  public ProdutoService() {
    super(Produto.class);
  }
}
