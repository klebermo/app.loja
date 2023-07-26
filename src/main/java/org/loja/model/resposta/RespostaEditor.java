package org.loja.model.resposta;

import java.beans.PropertyEditorSupport;

public class RespostaEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      RespostaService serv = new RespostaService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Resposta resposta = (Resposta) serv.findBy("id", id);
      setValue(resposta);
    } else {
      Resposta resposta = new Resposta();
      RespostaService serv = new RespostaService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      serv.insert(resposta);
      setValue(resposta);
    }
  }
}
