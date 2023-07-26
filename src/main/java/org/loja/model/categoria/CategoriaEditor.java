package org.loja.model.categoria;

import java.beans.PropertyEditorSupport;

public class CategoriaEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      CategoriaService serv = new CategoriaService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Categoria categoria = (Categoria)serv.findBy("id", id);
      setValue(categoria);
    } else {
      setValue(null);
    }
  }
}
