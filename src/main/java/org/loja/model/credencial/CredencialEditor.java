package org.loja.model.credencial;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CredencialEditor extends PropertyEditorSupport {
  @Autowired
  private CredencialService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Credencial credencial = serv.findBy("id", id);
      setValue(credencial);
    } else {
      setValue(null);
    }
  }
}
