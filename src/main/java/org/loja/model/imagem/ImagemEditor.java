package org.loja.model.imagem;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ImagemEditor extends PropertyEditorSupport {
  @Autowired
  private ImagemService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Imagem imagem = serv.findBy("id", id);
      setValue(imagem);
    } else {
      setValue(null);
    }
  }
}
