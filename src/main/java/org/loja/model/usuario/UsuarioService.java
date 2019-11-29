package org.loja.model.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends org.loja.model.Service<Usuario> {
  public UsuarioService() {
    super(Usuario.class);
  }
}
