package org.loja.model.pagina;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PaginaEditor extends PropertyEditorSupport {
  @Autowired
  private PaginaService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Pagina pagina = serv.findBy("id", id);
      setValue(pagina);
    } else {
      setValue(null);
    }
  }
}
