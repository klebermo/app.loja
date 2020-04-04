package org.loja.forum.resposta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("resposta")
public class RespostaController extends org.loja.forum.Controller<Resposta> {
  public RespostaController() {
    super(Resposta.class);
  }
}
