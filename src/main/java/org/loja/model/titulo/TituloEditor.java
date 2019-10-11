package org.loja.model.titulo;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class TituloEditor extends PropertyEditorSupport {
  @Autowired
  private TituloService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Titulo titulo = serv.findBy("id", id);
      setValue(titulo);
    } else {
      setValue(null);
    }
  }
}
