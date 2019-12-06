package org.loja.model.cesta;

import java.beans.PropertyEditorSupport;

public class CestaEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      CestaService serv = new CestaService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Cesta cesta = (Cesta) serv.findBy("id", id);
      setValue(cesta);
    } else {
      setValue(null);
    }
  }
}
