package org.loja.model.cesta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("cart")
public class CestaController extends org.loja.model.Controller<Cesta> {
  public CestaController() {
    super(Cesta.class);
  }

  @RequestMapping("/index")
  public String cesta(Model model) {
    model.addAttribute("cart", "cart");
    return "index";
  }
}
