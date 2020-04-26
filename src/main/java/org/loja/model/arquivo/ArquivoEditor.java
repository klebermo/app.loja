package org.loja.model.arquivo;

import java.beans.PropertyEditorSupport;

public class ArquivoEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      ArquivoService serv = new ArquivoService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Arquivo arquivo = (Arquivo) serv.findBy("id", id);
      arquivo.setVersion(arquivo.getVersion() + 1);
      setValue(arquivo);
    } else {
      setValue(null);
    }
  }
}
