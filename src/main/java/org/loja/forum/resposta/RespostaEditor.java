package org.loja.forum.resposta;

import java.beans.PropertyEditorSupport;

public class RespostaEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    RespostaDao dao = new RespostaDao();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(dao);

    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Resposta resposta = dao.findBy("id", id);
      setValue(resposta);
    } else {
      setValue(null);
    }
  }
}
