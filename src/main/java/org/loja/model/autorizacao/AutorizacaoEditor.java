package org.loja.model.autorizacao;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AutorizacaoEditor extends PropertyEditorSupport {
  @Autowired
  private AutorizacaoService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Autorizacao autorizacao = serv.findBy("id", id);
      setValue(autorizacao);
    } else {
      setValue(null);
    }
  }
}
