package org.loja.model.usuario;

import java.beans.PropertyEditorSupport;

public class UsuarioEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      UsuarioService serv = new UsuarioService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Usuario usuario = serv.findBy("id", id);
      setValue(usuario);
    } else {
      setValue(null);
    }
  }
}
