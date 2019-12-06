package org.loja.model.pedido;

import java.beans.PropertyEditorSupport;

public class PedidoEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      PedidoService serv = new PedidoService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Pedido pedido = (Pedido) serv.findBy("id", id);
      setValue(pedido);
    } else {
      setValue(null);
    }
  }
}
