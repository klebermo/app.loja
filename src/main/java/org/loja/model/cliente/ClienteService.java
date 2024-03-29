package org.loja.model.cliente;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.cesta.CestaDao;
import org.loja.model.cesta.Cesta;
import org.loja.model.pedido.PedidoDao;
import org.loja.model.pedido.Pedido;
import org.loja.model.produto.ProdutoDao;
import org.loja.model.produto.Produto;
import java.util.ArrayList;

@Service
public class ClienteService extends org.loja.model.Service<Cliente> {
  @Autowired
  private CestaDao cestaDao;

  @Autowired
  private PedidoDao pedidoDao;

  @Autowired
  private ProdutoDao produtoDao;

  public ClienteService() {
    super(Cliente.class);
  }

  public Integer cart_size(Cliente cliente) {
    if(cliente.getCesta() == null || cliente.getCesta().getProdutos() == null)
      return 0;
    return cliente.getCesta().getProdutos().size();
  }

  public Float cart_total(Cliente cliente) {
    if(cliente.getCesta() == null || cliente.getCesta().getProdutos() == null)
      return 0.f;
    Float total = 0.f;
    for(Produto produto : cliente.getCesta().getProdutos())
      total += produto.getPreco();
    return total;
  }

  public void add_to_cart(Cliente cliente, Integer produto_id) {
    if(cliente.getCesta() == null) {
      Cesta cesta = new Cesta();
      cestaDao.insert(cesta);
      cliente.setCesta(cesta);
      this.dao.update(cliente);
    }
    Cesta cesta = cliente.getCesta();
    if(cesta.getProdutos() == null) {
      cesta.setProdutos(new ArrayList<Produto>());
      cestaDao.update(cesta);
    }
    Produto produto = (Produto)produtoDao.findBy("id", produto_id);
    cesta.getProdutos().add(produto);
    cestaDao.update(cesta);
  }

  public void remove_from_cart(Cliente cliente, Integer produto_id) {
    Cesta cesta = cliente.getCesta();
    for(Produto produto : cesta.getProdutos()) {
      if(produto.getId() == produto_id) {
        cesta.getProdutos().remove(produto);
        cestaDao.update(cesta);
        break;
      }
    }
  }

  public Pedido insert_pedido(Integer cliente_id, Integer pedido_id) {
    Cliente cliente = (Cliente)this.dao.findBy("id", cliente_id);
    if(cliente.getPedidos() == null)
      cliente.setPedidos(new ArrayList<Pedido>());
    Pedido pedido = (Pedido)pedidoDao.findBy("id", pedido_id);
    cliente.getPedidos().add(pedido);
    this.dao.update(cliente);
    return pedido;
  }

  public Pedido update_pedido(Integer cliente_id, Integer pedido_id) {
    return null;
  }

  public Pedido delete_pedido(Integer cliente_id, Integer pedido_id) {
    Cliente cliente = (Cliente)this.dao.findBy("id", cliente_id);

    Pedido pedido = (Pedido)pedidoDao.findBy("id", pedido_id);
    cliente.getPedidos().remove(pedido);

    this.dao.update(cliente);
    return pedido;
  }
}
