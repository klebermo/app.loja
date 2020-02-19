package org.loja.ativacao.registro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroController extends org.loja.ativacao.Controller<Registro> {
  public RegistroController() {
    super(Registro.class);
  }
}
