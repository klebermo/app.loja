package org.loja.model.resumo;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ResumoEditor extends PropertyEditorSupport {
  @Autowired
  private ResumoService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Resumo resumo = serv.findBy("id", id);
      setValue(resumo);
    } else {
      setValue(null);
    }
  }
}
