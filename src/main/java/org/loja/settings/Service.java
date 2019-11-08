package org.loja.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import org.loja.model.usuario.UsuarioDao;
import org.loja.model.usuario.Usuario;
import org.loja.model.cesta.CestaDao;
import org.loja.model.cesta.Cesta;
import org.loja.model.pedido.PedidoDao;
import org.loja.model.pedido.Pedido;
import org.loja.model.produto.ProdutoDao;
import org.loja.model.produto.Produto;

import java.util.ArrayList;

public abstract class Service<E> {
  @Autowired
  protected Dao<E> dao;

  @Autowired
  protected UsuarioDao usuarioDao;

  @Autowired
  protected CestaDao cestaDao;

  @Autowired
  protected ProdutoDao produtoDao;

  @Autowired
  protected PedidoDao pedidoDao;

  @Autowired
  private JavaMailSender mailSender;

  protected Class<E> clazz;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public String create_order(Integer usuario_id, String transaction_id) {
    Usuario usuario = usuarioDao.findBy("id", usuario_id);
    return "/order/" + create_order(usuario, clazz.getSimpleName(), transaction_id);
  }

  public Integer create_order(Usuario usuario, String metodoPagamento, String transaction_id) {
    Pedido pedido = new Pedido();
    pedido.setProdutos(new ArrayList<Produto>());

    Cesta cesta = usuario.getCesta();
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

    if(usuario.getPedidos() == null) {
      usuario.setPedidos(new ArrayList<Pedido>());
      usuario.getPedidos().add(pedido);
      usuarioDao.update(usuario);
    } else {
      usuario.getPedidos().add(pedido);
      usuarioDao.update(usuario);
    }

    return pedido.getId();
  }

  public E get() {
    return dao.get();
  }

  public void set(E object) {
    dao.set(object);
  }
}
