package org.loja.model.pedido;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoDao extends Dao<Pedido> {
  public PedidoDao() {
    super(Pedido.class);
  }
}
