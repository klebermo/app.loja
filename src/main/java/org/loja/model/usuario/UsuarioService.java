package org.loja.model.usuario;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.credencial.Credencial;
import org.loja.model.credencial.CredencialDao;
import java.util.ArrayList;
import java.util.UUID;
import org.loja.model.cliente.Cliente;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

@Service
public class UsuarioService extends org.loja.model.Service<Usuario> {
  public UsuarioService() {
    super(Usuario.class);
  }

  @Autowired
  private UsuarioDao usuarioDao;

  @Autowired
  private CredencialDao credencialDao;

  @Autowired
  private HttpServletResponse response;

  public void register(Usuario novo, Cliente cliente) throws Exception {
    novo.setEnabled(true);
    novo.setLocked(false);
    novo.setCredenciais(new ArrayList<Credencial>());
    Credencial credencial = credencialDao.findBy("nome", "web");
    novo.getCredenciais().add(credencial);
    usuarioDao.insert(novo);

    org.loja.model.cliente.ClienteService clienteServ = new org.loja.model.cliente.ClienteService();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(clienteServ);
    cliente.setUsuario(novo);
    clienteServ.update(cliente);
    
    Cookie cookie = new Cookie("cliente", null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
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
