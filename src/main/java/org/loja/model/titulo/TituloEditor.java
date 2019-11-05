package org.loja.model.titulo;

import java.beans.PropertyEditorSupport;

public class TituloEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      TituloService serv = new TituloService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Titulo titulo = serv.findBy("id", id);
      setValue(titulo);
    } else {
      setValue(null);
    }
  }
}
