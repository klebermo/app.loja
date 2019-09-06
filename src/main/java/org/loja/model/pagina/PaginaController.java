package org.loja.model.pagina;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pagina")
public class PaginaController extends org.loja.model.Controller<Pagina> {
  public PaginaController() {
    super(Pagina.class);
  }
}
