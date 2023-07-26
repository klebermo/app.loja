package org.loja.model.categoria;

import org.springframework.stereotype.Service;
import org.loja.model.titulo.Titulo;

@Service
public class CategoriaService extends org.loja.model.Service<Categoria> {
  public CategoriaService() {
    super(Categoria.class);
  }

  public Categoria categoria(String titulo) {
    for(Object c : this.dao.select()) {
      for(Titulo t : ((Categoria)c).getNome()) {
        if(t.getConteudo().equals(titulo))
          return (Categoria)c;
      }
    }
    return null;
  }
}
