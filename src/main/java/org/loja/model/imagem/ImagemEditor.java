package org.loja.model.imagem;

import java.beans.PropertyEditorSupport;

public class ImagemEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      ImagemService serv = new ImagemService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Imagem imagem = serv.findBy("id", id);
      setValue(imagem);
    } else {
      setValue(null);
    }
  }
}
