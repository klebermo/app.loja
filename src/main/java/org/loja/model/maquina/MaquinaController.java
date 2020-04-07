package org.loja.model.maquina;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/maquina")
public class MaquinaController extends org.loja.model.Controller<Maquina> {
  public MaquinaController() {
    super(Maquina.class);
  }
}
