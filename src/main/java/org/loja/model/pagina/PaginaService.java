package org.loja.model.pagina;

import org.springframework.stereotype.Service;
import org.loja.model.pagina.PageTree.Node;
import org.loja.model.pagina.PageTree.Tree;
import java.util.List;
import java.util.Collections;
import org.loja.model.titulo.Titulo;

@Service
public class PaginaService extends org.loja.model.Service<Pagina> {
  public PaginaService() {
    super(Pagina.class);
  }

  public Pagina pagina(String titulo) {
    for(Pagina p : this.dao.select()) {
      for(Titulo t : p.getTitulo()) {
        if(t.getConteudo().equals(titulo))
          return p;
      }
    }
    return null;
  }

  public List<Node> breadcrumb(Pagina pagina) {
  	Tree tree = new Tree(this.select());
  	List<Node> lista = tree.search(pagina);
  	Collections.reverse(lista);
  	lista.remove(lista.size()-1);
  	return lista;
	}
}
