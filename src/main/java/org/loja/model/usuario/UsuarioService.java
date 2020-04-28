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
  private HttpServletResponse response;

  @Autowired
  private MailSender mailSender;

  public void register(Usuario novo, Cliente cliente) throws Exception {
    novo.setEnabled(true);
    novo.setLocked(false);
    usuarioDao.insert(novo);

    novo.setCredenciais(new ArrayList<Credencial>());
    Credencial c1 = credencialDao.findBy("nome", "web");
    Credencial c2 = credencialDao.findBy("nome", "topic");
    novo.getCredenciais().add(c1);
    novo.getCredenciais().add(c2);
    usuarioDao.update(novo);

    org.loja.model.cliente.ClienteService clienteServ = new org.loja.model.cliente.ClienteService();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(clienteServ);
    cliente.setUsuario(novo);
    clienteServ.update(cliente);

    Cookie cookie = new Cookie("cliente", null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);

    mailSender.sendMessage("kleber-mota@uol.com.br", novo.getEmail(), "Cadastro efetuado", "Seu cadastro no site foi efetuado com sucesso. Para efetuar login e finalizar suas compras, acesse: http://localhost:8080/");
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
