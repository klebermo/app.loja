package org.loja.model.cesta;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CestaEditor extends PropertyEditorSupport {
  @Autowired
  private CestaService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Cesta cesta = serv.findBy("id", id);
      setValue(cesta);
    } else {
      setValue(null);
    }
  }
}
