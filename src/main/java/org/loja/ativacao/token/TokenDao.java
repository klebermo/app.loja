package org.loja.ativacao.token;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDao extends Dao<Token> {
  public TokenDao() {
    super(Token.class);
  }
}
