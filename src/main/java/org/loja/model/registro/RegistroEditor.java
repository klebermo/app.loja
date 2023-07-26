package org.loja.model.registro;

import java.beans.PropertyEditorSupport;

public class RegistroEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      RegistroService serv = new RegistroService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Registro registro = (Registro) serv.findBy("id", id);
      setValue(registro);
    } else {
      Registro registro = new Registro();
      RegistroService serv = new RegistroService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      serv.insert(registro);
      setValue(registro);
    }
  }
}
