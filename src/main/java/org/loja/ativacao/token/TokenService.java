package org.loja.ativacao.token;

import org.springframework.stereotype.Service;

@Service
public class TokenService extends org.loja.ativacao.Service<Token> {
  public TokenService() {
    super(Token.class);
  }
}
