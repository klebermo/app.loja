package org.loja.settings;

import org.springframework.beans.factory.annotation.Autowired;

import org.loja.model.usuario.UsuarioDao;
import org.loja.model.usuario.Usuario;
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
  protected UsuarioDao usuarioDao;

  @Autowired
  protected CestaDao cestaDao;

  @Autowired
  protected ProdutoDao produtoDao;

  @Autowired
  protected PedidoDao pedidoDao;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public String create_order(Integer usuario_id, String transaction_id) throws MessagingException {
    Usuario usuario = usuarioDao.findBy("id", usuario_id);
    return "/order/" + create_order(usuario, clazz.getSimpleName(), transaction_id);
  }

  public Integer create_order(Usuario usuario, String metodoPagamento, String transaction_id) throws MessagingException {
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

    //mailSender.send_mail(usuario.getEmail(), usuario.getFirstName(), usuario.getLastName(), "Destalhes do pedido", "...");

    return pedido.getId();
  }

  public E get() {
    return dao.get();
  }

  public void set(E object) {
    dao.set(object);
  }
}
