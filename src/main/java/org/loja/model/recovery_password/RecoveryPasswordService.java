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
    Usuario usuario = usuarioDao.findBy("email", email);
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
      mailSender.sendMessage("kleber-mota@uol.com.br", usuario.getEmail(), "Alteração de senha", "Para confirmar a alteração de sua senha, acesse o link a seguir: http://localhost:8080/recover/password?token="+token);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void changePassword(String token, String senha) throws Exception {
    RecoveryPassword recoveryPassword = this.dao.findBy("token", token);

    if(recoveryPassword != null) {
      for(Usuario usuario : usuarioDao.select()) {
        if(usuario.getRecoveryPassword().contains(recoveryPassword)) {
          usuario.setPassword(senha);
          usuarioDao.update(usuario);
        }
      }
    } else {
      throw new Exception();
    }
  }
}
