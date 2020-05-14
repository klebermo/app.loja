package org.loja.model.usuario;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.credencial.Credencial;
import org.loja.model.credencial.CredencialDao;
import org.loja.model.cliente.Cliente;
import org.loja.model.cliente.ClienteDao;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import org.loja.MailSender;

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
  private ClienteDao clienteDao;

  @Autowired
  private HttpServletResponse response;

  @Autowired
  private MailSender mailSender;

  public void register(Usuario novo, Cliente cliente) {
    novo.setEnabled(true);
    novo.setLocked(false);
    usuarioDao.insert(novo);

    novo.setCredenciais(new ArrayList<Credencial>());
    Credencial c1 = credencialDao.findBy("nome", "web");
    Credencial c2 = credencialDao.findBy("nome", "topic");
    novo.getCredenciais().add(c1);
    novo.getCredenciais().add(c2);
    usuarioDao.update(novo);

    cliente.setUsuario(novo);
    clienteDao.update(cliente);

    try {
      mailSender.sendHTMLMessage(novo.getEmail(), "Cadastro efetuado", "email/register", novo, new java.util.Locale("pt", "br"));
    } catch (Exception e) {
      e.printStackTrace();
    }
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
