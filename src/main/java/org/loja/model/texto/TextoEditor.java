package org.loja.model.texto;

import java.beans.PropertyEditorSupport;

public class TextoEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      TextoService serv = new TextoService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Texto texto = (Texto) serv.findBy("id", id);
      setValue(texto);
    } else {
      setValue(null);
    }
  }
}
