package org.loja.model.credencial;

import java.beans.PropertyEditorSupport;

public class CredencialEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      CredencialService serv = new CredencialService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Credencial credencial = (Credencial) serv.findBy("id", id);
      setValue(credencial);
    } else {
      setValue(null);
    }
  }
}
