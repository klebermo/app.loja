package org.loja.model.pagina;

import java.beans.PropertyEditorSupport;

public class PaginaEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      PaginaService serv = new PaginaService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Pagina pagina = (Pagina) serv.findBy("id", id);
      setValue(pagina);
    } else {
      setValue(null);
    }
  }
}
