package org.loja.model.usuario;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UsuarioEditor extends PropertyEditorSupport {
  @Autowired
  private UsuarioService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Usuario usuario = serv.findBy("id", id);
      setValue(usuario);
    } else {
      setValue(null);
    }
  }
}
