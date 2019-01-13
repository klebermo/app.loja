package org.loja.model.categoria;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categoria")
public class CategoriaController extends org.loja.model.Controller<Categoria> {
  public CategoriaController() {
    super(Categoria.class);
  }
}
