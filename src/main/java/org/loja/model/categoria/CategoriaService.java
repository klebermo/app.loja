package org.loja.model.categoria;

import org.springframework.stereotype.Service;
import org.loja.model.titulo.Titulo;

@Service
public class CategoriaService extends org.loja.model.Service<Categoria> {
  public CategoriaService() {
    super(Categoria.class);
  }

  public Categoria categoria(String titulo) {
    for(Categoria c : this.dao.select()) {
      for(Titulo t : c.getNome()) {
        if(t.getConteudo().equals(titulo))
          return c;
      }
    }
    return null;
  }
}
