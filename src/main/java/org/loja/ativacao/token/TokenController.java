package org.loja.ativacao.token;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/token")
public class TokenController extends org.loja.ativacao.Controller<Token> {
  public TokenController() {
    super(Token.class);
  }
}
