package org.loja.model.produto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("produto")
public class ProdutoController extends org.loja.model.Controller<Produto> {
  public ProdutoController() {
    super(Produto.class);
  }

  @RequestMapping("/{nome}")
  public String produto(Model model, @PathVariable("nome") String nome) {
    model.addAttribute("produto", this.serv.findBy("nome", nome));
    return "index";
  }
}
