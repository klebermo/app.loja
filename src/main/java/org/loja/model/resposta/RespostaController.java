package org.loja.model.resposta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("resposta")
public class RespostaController extends org.loja.model.Controller<Resposta> {
  public RespostaController() {
    super(Resposta.class);
  }
}
