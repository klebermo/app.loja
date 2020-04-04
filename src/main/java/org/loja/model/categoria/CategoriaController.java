package org.loja.model.categoria;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("categoria")
public class CategoriaController extends org.loja.model.Controller<Categoria> {
  public CategoriaController() {
    super(Categoria.class);
  }

  @Autowired
  private CategoriaService serv;

  @RequestMapping("/{nome}")
  public String categoria(Model model, @PathVariable("nome") String nome) {
    model.addAttribute("categoria", this.serv.categoria(nome));
    return "index";
  }
}
