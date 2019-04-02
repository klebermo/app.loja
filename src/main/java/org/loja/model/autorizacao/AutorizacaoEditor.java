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
    System.out.println("text='"+text+"'");
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      System.out.println("number='"+id+"'");
      Autorizacao autorizacao = serv.findBy("id", id);
      System.out.println("autorizacao='"+autorizacao+"'");
      setValue(autorizacao);
    } else {
      setValue(null);
    }
  }
}
