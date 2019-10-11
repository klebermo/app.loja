package org.loja.model.texto;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class TextoEditor extends PropertyEditorSupport {
  @Autowired
  private TextoService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Texto texto = serv.findBy("id", id);
      setValue(texto);
    } else {
      setValue(null);
    }
  }
}
