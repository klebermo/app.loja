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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.math.BigDecimal;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;

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

  Map<String, String> map = new HashMap<String, String>();

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

  public String checkout_paypal(Integer usuario_id, String payerId, String guid) throws com.paypal.base.rest.PayPalRESTException {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    Payment createdPayment = null;

    org.loja.settings.paypal.Paypal paypal = (org.loja.settings.paypal.Paypal) paypalDao.get();
    String appId = paypal.getClientId();
    String appKey = paypal.getClientSecret();
    APIContext apiContext = new APIContext(appId, appKey, "sandbox");

    if (payerId != null) {
      Payment payment = new Payment();
			if (guid != null) {
        payment.setId(map.get(guid));
        PaymentExecution paymentExecution = new PaymentExecution();
			  paymentExecution.setPayerId(payerId);
        createdPayment = payment.execute(apiContext, paymentExecution);
      }
      return  "/order/"+create_order(usuario, "paypal").toString();
    } else {
      System.out.println("usuario: "+usuario);
      Details details = new Details();
      details.setSubtotal(String.valueOf(cart_total(usuario.getId())));

      Amount amount = new Amount();
      amount.setCurrency("BRL");
      amount.setTotal(String.valueOf(cart_total(usuario.getId())));
      amount.setDetails(details);

      Transaction transaction = new Transaction();
      transaction.setAmount(amount);

      ItemList itemList = new ItemList();
      List<Item> items = new ArrayList<Item>();
      for(Produto p : usuario.getCesta().getProdutos()) {
        Item item = new Item();
        item.setName(p.getNome()).setQuantity("1").setCurrency("BRL").setPrice(String.valueOf(p.getPreco()));
        items.add(item);
      }
      itemList.setItems(items);
      transaction.setItemList(itemList);

      List<Transaction> transactions = new ArrayList<Transaction>();
      transactions.add(transaction);

      Payer payer = new Payer();
      payer.setPaymentMethod("paypal");

      Payment payment = new Payment();
      payment.setIntent("sale");
      payment.setPayer(payer);
      payment.setTransactions(transactions);

      RedirectUrls redirectUrls = new RedirectUrls();
      guid = UUID.randomUUID().toString().replaceAll("-", "");
      redirectUrls.setCancelUrl("http://localhost:8080//cart");
      redirectUrls.setReturnUrl("http://localhost:8080/usuario/checkout_paypal?usuario_id="+usuario.getId().toString()+"&guid="+guid);
      payment.setRedirectUrls(redirectUrls);

      createdPayment = payment.create(apiContext);
      Iterator<Links> links = createdPayment.getLinks().iterator();
      String url = null;
      while (links.hasNext()) {
        Links link = links.next();
        if (link.getRel().equalsIgnoreCase("approval_url")) {
          url = link.getHref();
        }
      }
      map.put(guid, createdPayment.getId());
      return url;
    }
  }

  public String checkout_mercadopago(Integer usuario_id) throws com.mercadopago.exceptions.MPException {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    System.out.println("---");

    /*String publicKey = ((org.loja.settings.mercadopago.MercadoPago) mercadoPagoDao.get()).getPublicKey();
    System.out.println("publicKey= "+publicKey);
    com.mercadopago.MercadoPago.SDK.setPublicKey(publicKey);*/

    String accessToken = ((org.loja.settings.mercadopago.MercadoPago) mercadoPagoDao.get()).getAccessToken();
    System.out.println("accessToken= "+accessToken);
    com.mercadopago.MercadoPago.SDK.setAccessToken(accessToken);

    /*String clientId = ((org.loja.settings.mercadopago.MercadoPago) mercadoPagoDao.get()).getClientId();
    System.out.println("clientId= "+clientId);
    com.mercadopago.MercadoPago.SDK.setClientId(clientId);

    String clientSecret = ((org.loja.settings.mercadopago.MercadoPago) mercadoPagoDao.get()).getClientSecret();
    System.out.println("clientSecret= "+clientSecret);
    com.mercadopago.MercadoPago.SDK.setClientSecret(clientSecret);*/

    System.out.println("---");
    com.mercadopago.resources.Payment payment = new com.mercadopago.resources.Payment()
            .setTransactionAmount(cart_total(usuario.getId()))
            .setDescription("loja-de-software.net.br")
            .setInstallments(1)
            .setPayer(new com.mercadopago.resources.datastructures.payment.Payer().setEmail(usuario.getEmail()));

    payment.save();

    System.out.println("payment.status= "+payment.getStatus());

    if(payment.getStatus() == null)
      return "/";

    return "/order/"+create_order(usuario, "mercadopago").toString();
  }

  public String checkout_pagseguro(Integer usuario_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    return  "/order/"+create_order(usuario, "pagseguro").toString();
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
