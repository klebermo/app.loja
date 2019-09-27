package org.loja.model.usuario;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.loja.model.credencial.CredencialDao;
import org.loja.model.credencial.Credencial;
import org.loja.model.cesta.CestaDao;
import org.loja.model.cesta.Cesta;
import org.loja.model.pedido.PedidoDao;
import org.loja.model.pedido.Pedido;
import org.loja.model.produto.ProdutoDao;
import org.loja.model.produto.Produto;
import org.loja.settings.paypal.PaypalDao;
import org.loja.settings.mercadopago.MercadoPagoDao;
import org.loja.settings.pagseguro.PagSeguroDao;
import java.util.List;
import java.util.ArrayList;

@Service
public class UsuarioService extends org.loja.model.Service<Usuario> {
  @Autowired
  private UsuarioDao dao;

  @Autowired
  private CredencialDao credencialDao;

  @Autowired
  private CestaDao cestaDao;

  @Autowired
  private PedidoDao pedidoDao;

  @Autowired
  private ProdutoDao produtoDao;

  @Autowired
  private PaypalDao paypalDao;

  @Autowired
  private MercadoPagoDao mercadoPagoDao;

  @Autowired
  private PagSeguroDao pagSeguroDao;

  public UsuarioService() {
    super(Usuario.class);
  }

  public Boolean register(Usuario novo) {
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(4);
    String senha = bcrypt.encode(novo.getPassword());
    novo.setPassword(senha);
    novo.setEnabled(true);
    this.dao.insert(novo);
    return true;
  }

  public Boolean recoverPassword(String email) {
    return true;
  }

  public void toggle_credencial(Integer usuario_id, Integer credencial_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    Credencial credencial = credencialDao.findBy("id", credencial_id);
    if(usuario.getCredenciais() == null) {
      usuario.setCredenciais(new ArrayList<Credencial>());
      usuario.getCredenciais().add(credencial);
      this.dao.update(usuario);
    } else {
      if(usuario.getCredenciais().contains(credencial)) {
        usuario.getCredenciais().remove(credencial);
        this.dao.update(usuario);
      } else {
        usuario.getCredenciais().add(credencial);
        this.dao.update(usuario);
      }
    }
  }

  public Integer cart_size(Integer usuario_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    if(usuario.getCesta() == null || usuario.getCesta().getProdutos() == null)
      return 0;
    return usuario.getCesta().getProdutos().size();
  }

  public Float cart_total(Integer usuario_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    if(usuario.getCesta() == null || usuario.getCesta().getProdutos() == null)
      return 0.f;
    Float total = 0.f;
    for(Produto produto : usuario.getCesta().getProdutos())
      total += produto.getPreco();
    return total;
  }

  public void add_to_cart(Integer usuario_id, Integer produto_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    if(usuario.getCesta() == null) {
      Cesta cesta = new Cesta();
      cestaDao.insert(cesta);
      usuario.setCesta(cesta);
      this.dao.update(usuario);
    }
    Cesta cesta = usuario.getCesta();
    if(cesta.getProdutos() == null) {
      cesta.setProdutos(new ArrayList<Produto>());
      cestaDao.update(cesta);
    }
    Produto produto = produtoDao.findBy("id", produto_id);
    cesta.getProdutos().add(produto);
    cestaDao.update(cesta);
  }

  public void remove_from_cart(Integer usuario_id, Integer produto_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    Cesta cesta = usuario.getCesta();
    for(Produto produto : cesta.getProdutos()) {
      if(produto.getId() == produto_id) {
        cesta.getProdutos().remove(produto);
        cestaDao.update(cesta);
        break;
      }
    }
  }

  public String checkout_paypal(Integer usuario_id, String payerId, String guid) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    org.loja.payment.paypal.Checkout checkout = new org.loja.payment.paypal.Checkout();
    if(checkout.register(payerId, guid))
      return  "/order/"+create_order(usuario, "paypal").toString();
    else
      return "/cart";
  }

  public String checkout_mercadopago(Integer usuario_id, String status_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    org.loja.payment.mercadopago.Checkout checkout = new org.loja.payment.mercadopago.Checkout();
    if(checkout.register())
      return  "/order/"+create_order(usuario, "mercadoPago").toString();
    else
      return "/order";
  }

  public String checkout_pagseguro(Integer usuario_id, Integer status_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    org.loja.payment.pagseguro.Checkout checkout = new org.loja.payment.pagseguro.Checkout();
    if(checkout.register())
      return  "/order/"+create_order(usuario, "paypal").toString();
    else
      return "/cart";
  }

  public Integer create_order(Usuario usuario, String metogoPagamento) {
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
    pedido.setMetogoPagamento(metogoPagamento);
    pedidoDao.insert(pedido);
    if(usuario.getPedidos() == null) {
      usuario.setPedidos(new ArrayList<Pedido>());
      usuario.getPedidos().add(pedido);
      this.dao.update(usuario);
    } else {
      usuario.getPedidos().add(pedido);
      this.dao.update(usuario);
    }
    return pedido.getId();
  }
}
