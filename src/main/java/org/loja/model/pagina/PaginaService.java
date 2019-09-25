package org.loja.model.pagina;

import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.loja.model.pagina.PageTree.Node;
import org.loja.model.pagina.PageTree.Tree;
import java.util.List;
import java.util.Collections;
=======
>>>>>>> master

@Service
public class PaginaService extends org.loja.model.Service<Pagina> {
  public PaginaService() {
    super(Pagina.class);
  }
<<<<<<< HEAD

  public List<Node> breadcrumb(Pagina pagina) {
  	Tree tree = new Tree(this.select());
  	List<Node> lista = tree.search(pagina);
  	Collections.reverse(lista);
  	lista.remove(lista.size()-1);
  	return lista;
	}
=======
>>>>>>> master
}
