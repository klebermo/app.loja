package org.loja;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.cliente.ClienteDao;
import org.loja.model.cliente.Cliente;
import org.loja.model.usuario.UsuarioDao;
import org.loja.model.usuario.Usuario;
import org.loja.model.credencial.CredencialDao;
import org.loja.model.credencial.Credencial;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class HomeService {
  @Autowired
  private ClienteDao clienteDao;

  @Autowired
  private UsuarioDao usuarioDao;

  @Autowired
  private CredencialDao credencialDao;

  @Autowired
  private MailSender mailSender;

  public void register(Usuario novo) throws Exception {
    Cliente cliente = new Cliente();
    clienteDao.insert(cliente);

    novo.setEnabled(true);
    novo.setLocked(false);
    novo.setCredenciais(new ArrayList<Credencial>());
    Credencial credencial = credencialDao.findBy("nome", "web");
    novo.getCredenciais().add(credencial);
    usuarioDao.insert(novo);

    cliente.setUsuario(novo);
    clienteDao.update(cliente);
    //mailSender.send_mail(novo.getEmail(), novo.getFirstName(), novo.getLastName(), "Confirmação de cadastro", "...");
  }

  public String recoverPassword(String email, String token) throws Exception {
    Usuario usuario = usuarioDao.findBy("email", email);
    if(usuario != null)
      if(token == null) {
        token = UUID.randomUUID().toString();
        //mailSender.send_mail(usuario.getEmail(), usuario.getFirstName(), usuario.getLastName(), "Recuperação de senha", "...");
        return "/recoverPassword?email="+email+"&token="+token;
      } else {
        //mailSender.send_mail(usuario.getEmail(), usuario.getFirstName(), usuario.getLastName(), "Nova senha", "...");
        return "/login";
      }
    else
      return "/recoverPassword";
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
