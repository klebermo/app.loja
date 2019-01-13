package org.loja.model.arquivo;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ArquivoEditor extends PropertyEditorSupport {
  @Autowired
  private ArquivoService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Arquivo arquivo = serv.findBy("id", id);
      setValue(arquivo);
    } else {
      setValue(null);
    }
  }
}
