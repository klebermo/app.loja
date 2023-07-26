package org.loja.model.pedido;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.produto.ProdutoDao;
import org.loja.model.produto.Produto;
import java.util.ArrayList;

@Service
public class PedidoService extends org.loja.model.Service<Pedido> {
  @Autowired
  private ProdutoDao produtoDao;

  public PedidoService() {
    super(Pedido.class);
  }

  public Pedido insert_produto(Integer pedido_id, Integer produto_id) {
    Pedido pedido = (Pedido)this.dao.findBy("id", pedido_id);
    Produto produto = (Produto)produtoDao.findBy("id", produto_id);
    if(pedido.getProdutos() == null)
      pedido.setProdutos(new ArrayList<Produto>());
    pedido.getProdutos().add(produto);
    return this.dao.update(pedido);
  }

  public Pedido delete_produto(Integer pedido_id, Integer produto_id) {
    Pedido pedido = (Pedido)this.dao.findBy("id", pedido_id);
    Produto produto = (Produto)produtoDao.findBy("id", produto_id);
    pedido.getProdutos().remove(produto);
    return this.dao.update(pedido);
  }
}
