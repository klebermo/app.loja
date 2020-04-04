package org.loja.model.usuario;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.credencial.Credencial;
import org.loja.model.credencial.CredencialDao;
import java.util.ArrayList;

@Service
public class UsuarioService extends org.loja.model.Service<Usuario> {
  public UsuarioService() {
    super(Usuario.class);
  }

  @Autowired
  private UsuarioDao usuarioDao;

  @Autowired
  private CredencialDao credencialDao;

  public void register(Usuario novo) throws Exception {
    novo.setEnabled(true);
    novo.setLocked(false);
    novo.setCredenciais(new ArrayList<Credencial>());
    Credencial credencial = credencialDao.findBy("nome", "web");
    novo.getCredenciais().add(credencial);
    usuarioDao.insert(novo);
  }

  public void toggle_credencial(Integer usuario_id, Integer credencial_id) {
    Usuario usuario = usuarioDao.findBy("id", usuario_id);
    Credencial credencial = credencialDao.findBy("id", credencial_id);
    if(usuario.getCredenciais() == null) {
      usuario.setCredenciais(new ArrayList<Credencial>());
      usuario.getCredenciais().add(credencial);
      usuarioDao.update(usuario);
    } else {
      if(usuario.getCredenciais().contains(credencial)) {
        usuario.getCredenciais().remove(credencial);
        usuarioDao.update(usuario);
      } else {
        usuario.getCredenciais().add(credencial);
        usuarioDao.update(usuario);
      }
    }
  }
}
