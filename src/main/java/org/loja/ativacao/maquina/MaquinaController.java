package org.loja.ativacao.maquina;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/maquina")
public class MaquinaController extends org.loja.ativacao.Controller<Maquina> {
  public MaquinaController() {
    super(Maquina.class);
  }
}
