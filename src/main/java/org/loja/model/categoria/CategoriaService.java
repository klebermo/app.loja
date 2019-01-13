package org.loja.model.categoria;

import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends org.loja.model.Service<Categoria> {
  public CategoriaService() {
    super(Categoria.class);
  }
}
