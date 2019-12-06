package org.loja.model.resumo;

import java.beans.PropertyEditorSupport;

public class ResumoEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      ResumoService serv = new ResumoService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Resumo resumo = (Resumo) serv.findBy("id", id);
      setValue(resumo);
    } else {
      setValue(null);
    }
  }
}
