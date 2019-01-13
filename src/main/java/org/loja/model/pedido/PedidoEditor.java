package org.loja.model.pedido;

import org.springframework.stereotype.Component;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PedidoEditor extends PropertyEditorSupport {
  @Autowired
  private PedidoService serv;

  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Pedido pedido = serv.findBy("id", id);
      setValue(pedido);
    } else {
      setValue(null);
    }
  }
}
