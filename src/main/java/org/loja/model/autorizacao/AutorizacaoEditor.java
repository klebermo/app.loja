package org.loja.model.autorizacao;

import java.beans.PropertyEditorSupport;

public class AutorizacaoEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      AutorizacaoService serv = new AutorizacaoService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Autorizacao autorizacao = serv.findBy("id", id);
      setValue(autorizacao);
    } else {
      setValue(null);
    }
  }
}
