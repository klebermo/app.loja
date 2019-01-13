package org.loja.model.usuario;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDao extends Dao<Usuario> {
  public UsuarioDao() {
    super(Usuario.class);
  }
}
