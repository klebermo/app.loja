package org.loja.model.pedido;

import org.springframework.stereotype.Service;

@Service
public class PedidoService extends org.loja.model.Service<Pedido> {
  public PedidoService() {
    super(Pedido.class);
  }
}
