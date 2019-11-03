package org.loja.model.pedido;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pedido")
public class PedidoController extends org.loja.model.Controller<Pedido> {
  public PedidoController() {
    super(Pedido.class);
  }
}
