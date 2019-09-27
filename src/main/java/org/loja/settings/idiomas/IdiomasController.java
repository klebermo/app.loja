package org.loja.settings.idiomas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("idiomas")
public class IdiomasController extends org.loja.settings.Controller<Idiomas> {
  public IdiomasController() {
    super(Idiomas.class);
  }
}
