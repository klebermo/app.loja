package org.loja.settings;

import org.springframework.beans.factory.annotation.Autowired;

import org.loja.model.cliente.ClienteDao;
import org.loja.model.cliente.Cliente;
import org.loja.model.cesta.CestaDao;
import org.loja.model.cesta.Cesta;
import org.loja.model.pedido.PedidoDao;
import org.loja.model.pedido.Pedido;
import org.loja.model.produto.ProdutoDao;
import org.loja.model.produto.Produto;
import org.loja.MailSender;

import java.util.ArrayList;
import javax.mail.MessagingException;

public abstract class Service<E> {
  protected Class<E> clazz;

  @Autowired
  protected Dao<E> dao;

  @Autowired
  protected ClienteDao clienteDao;

  @Autowired
  protected CestaDao cestaDao;

  @Autowired
  protected PedidoDao pedidoDao;

  @Autowired
  protected ProdutoDao produtoDao;

  @Autowired
  private MailSender mailSender;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public String create_order(Integer cliente_id, String transaction_id) throws MessagingException {
    Cliente cliente = (Cliente) clienteDao.findBy("id", cliente_id);
    return "/order/" + create_order(cliente, clazz.getSimpleName(), transaction_id);
  }

  public Integer create_order(Cliente cliente, String metodoPagamento, String transaction_id) throws MessagingException {
    Pedido pedido = new Pedido();
    pedido.setProdutos(new ArrayList<Produto>());

    Cesta cesta = cliente.getCesta();
    if(cesta.getProdutos() != null) {
      for(Produto produto : cesta.getProdutos())
        pedido.getProdutos().add(produto);
      cesta.getProdutos().clear();
      cestaDao.update(cesta);
    }

    pedido.setDataCompra(new java.util.Date());
    pedido.setMetodoPagamento(metodoPagamento);
    pedido.setTransactionId(transaction_id);
    pedidoDao.insert(pedido);

    if(cliente.getPedidos() == null) {
      cliente.setPedidos(new ArrayList<Pedido>());
      cliente.getPedidos().add(pedido);
      clienteDao.update(cliente);
    } else {
      cliente.getPedidos().add(pedido);
      clienteDao.update(cliente);
    }

    //mailSender.send_mail(cliente.getEmail(), cliente.getFirstName(), cliente.getLastName(), "Destalhes do pedido", "...");

    return pedido.getId();
  }

  public E get() {
    return dao.get();
  }

  public void set(E object) {
    dao.set(object);
  }
}
