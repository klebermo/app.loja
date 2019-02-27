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
import java.util.HashSet;
import java.util.UUID;
import java.util.Iterator;
import com.paypal.base.rest.APIContext;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.PaymentExecution;
import javax.servlet.http.HttpServletRequest;

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
  private HttpServletRequest request;

  java.util.Map<String, String> map = new java.util.HashMap<String, String>();

  public UsuarioService() {
    super(Usuario.class);
  }

  public void register(Usuario novo) {
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(4);
    String senha = bcrypt.encode(novo.getPassword());
    novo.setPassword(senha);
    novo.setEnabled(true);
    this.dao.insert(novo);
  }

  public void toggle_credencial(Integer usuario_id, Integer credencial_id) {
    Usuario usuario = this.dao.findBy("id", usuario_id);
    Credencial credencial = credencialDao.findBy("id", credencial_id);
    if(usuario.getCredenciais() == null) {
      usuario.setCredenciais(new HashSet<Credencial>());
      usuario.getCredenciais().add(credencial);
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
      cesta.setProdutos(new HashSet<Produto>());
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

  public String checkout(Integer usuario_id, String payerId, String guid) throws com.paypal.base.rest.PayPalRESTException {
    Usuario usuario = this.dao.findBy("id", usuario_id);

    String clientId = paypalDao.get().getClientId();
    String clientSecret = paypalDao.get().getClientSecret();
    APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

    String redirectURL = "";
    if(payerId != null) {
      if(guid != null) {
        Payment payment = new Payment();
        payment.setId(map.get(guid));
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        payment.execute(apiContext, paymentExecution);

        Pedido pedido = new Pedido();
        pedido.setProdutos(new HashSet<Produto>());
        Cesta cesta = usuario.getCesta();
        if(cesta.getProdutos() != null) {
          for(Produto produto : cesta.getProdutos())
            pedido.getProdutos().add(produto);
          cesta.getProdutos().clear();
          cestaDao.update(cesta);
        }
        pedido.setDataCompra(new java.util.Date());
        pedidoDao.insert(pedido);
        if(usuario.getPedidos() == null) {
          usuario.setPedidos(new HashSet<Pedido>());
          usuario.getPedidos().add(pedido);
          this.dao.update(usuario);
        } else {
          usuario.getPedidos().add(pedido);
          this.dao.update(usuario);
        }
        redirectURL =  "/order/"+pedido.getId().toString();
      }
    } else {
      Payment createdPayment = createPayment(usuario, apiContext);
      Iterator<Links> links = createdPayment.getLinks().iterator();
      while (links.hasNext()) {
        Links link = links.next();
        if (link.getRel().equalsIgnoreCase("approval_url"))
          redirectURL = link.getHref();
      }
      map.put(guid, createdPayment.getId());
    }
    return redirectURL;
  }

  public Payment createPayment(Usuario usuario, APIContext apiContext) throws com.paypal.base.rest.PayPalRESTException  {
    Amount amount = new Amount();
    amount.setCurrency("BRL");
    amount.setTotal(this.cart_total(usuario.getId()).toString());

    String desc = "";
    for(Produto produto : usuario.getCesta().getProdutos())
      desc = desc + "* " + produto.getNome() + "\n";

    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    transaction.setDescription(desc);

    java.util.List<Transaction> transactions = new java.util.ArrayList<Transaction>();
    transactions.add(transaction);

    Payer payer = new Payer();
    payer.setPaymentMethod("paypal");

    Payment payment = new Payment();
    payment.setIntent("sale");
    payment.setPayer(payer);
    payment.setTransactions(transactions);

    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl(request.getContextPath() + "/cancel");
    redirectUrls.setReturnUrl(request.getContextPath() + "/checkout?usuario_id="+usuario.getId()+"?guid="+UUID.randomUUID().toString());
    payment.setRedirectUrls(redirectUrls);

    return payment.create(apiContext);
  }
}
