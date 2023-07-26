package org.loja.model.recovery_password;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.MailSender;
import java.util.UUID;
import java.util.Date;
import java.util.HashSet;
import org.loja.model.usuario.UsuarioDao;
import org.loja.model.usuario.Usuario;

@Service
public class RecoveryPasswordService extends org.loja.model.Service<RecoveryPassword> {
  public RecoveryPasswordService() {
    super(RecoveryPassword.class);
  }

  @Autowired
  private MailSender mailSender;

  @Autowired
  private UsuarioDao usuarioDao;

  public void recoverPassword(String email) {
    Usuario usuario = (Usuario)usuarioDao.findBy("email", email);
    String token = UUID.randomUUID().toString().replaceAll("-", "");

    RecoveryPassword recoveryPassword = new RecoveryPassword();
    recoveryPassword.setToken(token);
    recoveryPassword.setDataPedido(new Date());
    this.dao.insert(recoveryPassword);

    if(usuario.getRecoveryPassword() == null)
      usuario.setRecoveryPassword(new HashSet<RecoveryPassword>());

    usuario.getRecoveryPassword().add(recoveryPassword);
    usuarioDao.update(usuario);

    try {
      mailSender.sendHTMLMessage(usuario.getEmail(), "Alteração de senha", "email/recover", new String(token), new java.util.Locale("pt", "br"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void changePassword(String token, String senha) throws Exception {
    RecoveryPassword recoveryPassword = (RecoveryPassword)this.dao.findBy("token", token);

    if(recoveryPassword != null) {
      for(Object usuario : usuarioDao.select()) {
        if(((Usuario)usuario).getRecoveryPassword().contains(recoveryPassword)) {
          ((Usuario)usuario).setPassword(senha);
          usuarioDao.update((Usuario)usuario);
        }
      }
    } else {
      throw new Exception();
    }
  }
}
