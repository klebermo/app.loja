package org.loja.model.categoria;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CategoriaEditor extends PropertyEditorSupport {
  @Autowired
  private CategoriaService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Categoria categoria = serv.findBy("id", id);
      setValue(categoria);
    } else {
      setValue(null);
    }
  }
}
