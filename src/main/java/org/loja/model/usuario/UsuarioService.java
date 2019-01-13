package org.loja.model.usuario;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioService extends org.loja.model.Service<Usuario> {
  @Autowired
  private UsuarioDao dao;

  public UsuarioService() {
    super(Usuario.class);
  }

  public void register(Usuario novo) {
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(4);
    String senha = bcrypt.encode(novo.getPassword());
    novo.setPassword(senha);
    novo.setEnabled(true);
    this.dao.insert(novo);
  }
}
