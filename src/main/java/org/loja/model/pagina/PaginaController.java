package org.loja.model.pagina;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("pagina")
public class PaginaController extends org.loja.model.Controller<Pagina> {
  public PaginaController() {
    super(Pagina.class);
  }

  @Autowired
  private PaginaService serv;

  @RequestMapping("/{titulo}")
  public String pagina(Model model, @PathVariable("titulo") String titulo) {
    Pagina pagina = this.serv.pagina(titulo);
    model.addAttribute("pagina", pagina);
    model.addAttribute("breadcrumb", this.serv.breadcrumb(pagina));
    return "index";
  }
}
