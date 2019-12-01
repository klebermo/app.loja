package org.loja.model.cliente;

import java.beans.PropertyEditorSupport;

public class ClienteEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      ClienteService serv = new ClienteService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Cliente cliente = serv.findBy("id", id);
      setValue(cliente);
    } else {
      setValue(null);
    }
  }
}
