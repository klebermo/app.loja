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

  public void add_produto(Integer pedido_id, Integer produto_id) {
    Pedido pedido = this.dao.findBy("id", pedido_id);
    if(pedido.getProdutos() == null)
      pedido.setProdutos(new ArrayList<Produto>());
    Produto produto = produtoDao.findBy("id", produto_id);
    pedido.getProdutos().add(produto);
    this.dao.update(pedido);
  }

  public void remove_produto(Integer pedido_id, Integer produto_id) {
    Pedido pedido = this.dao.findBy("id", pedido_id);

    Produto produto = produtoDao.findBy("id", produto_id);
    pedido.getProdutos().remove(produto);

    this.dao.update(pedido);
  }
}
