package org.loja.model.maquina;

import java.beans.PropertyEditorSupport;
import org.loja.model.maquina.Maquina;
import org.loja.model.maquina.MaquinaService;

public class MaquinaEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      MaquinaService serv = new MaquinaService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Maquina maquina = (Maquina) serv.findBy("id", id);
      setValue(maquina);
    } else {
      Maquina maquina = new Maquina();
      MaquinaService serv = new MaquinaService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      serv.insert(maquina);
      setValue(maquina);
    }
  }
}
