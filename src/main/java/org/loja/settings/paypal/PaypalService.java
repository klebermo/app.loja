package org.loja.settings.paypal;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.paypal.base.rest.PayPalRESTException;
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
import org.loja.model.cliente.Cliente;
import org.loja.model.cliente.ClienteDao;
import org.loja.model.cesta.Cesta;
import org.loja.model.cesta.CestaDao;
import org.loja.model.pedido.Pedido;
import org.loja.model.pedido.PedidoDao;
import org.loja.model.produto.Produto;
import java.util.Iterator;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.loja.MailSender;

@Service
public class PaypalService extends org.loja.settings.Service<Paypal> {
  @Autowired
  private ClienteDao clienteDao;

  @Autowired
  private CestaDao cestaDao;

  @Autowired
  private PedidoDao pedidoDao;

  @Autowired
  private MailSender mailSender;

  private Map<String, String> map = new HashMap<String, String>();

  public PaypalService() {
    super(Paypal.class);
  }

  public String checkout(Integer cliente_id, String payerId, String guid) throws PayPalRESTException {
    Cliente cliente = (Cliente) clienteDao.findBy("id", cliente_id);
    map.put("cliente_id", cliente.getId().toString());

    Payment createdPayment = null;

    Paypal paypal = (Paypal) this.dao.get();
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
      return this.create_order(createdPayment.getId());
    } else {
      Float total = 0.f;
      for(Produto produto : cliente.getCesta().getProdutos())
        total += produto.getPreco();

      Details details = new Details();
      details.setSubtotal(total.toString());

      Amount amount = new Amount();
      amount.setCurrency("BRL");
      amount.setTotal(total.toString());
      amount.setDetails(details);

      Transaction transaction = new Transaction();
      transaction.setAmount(amount);

      ItemList itemList = new ItemList();
      List<Item> items = new ArrayList<Item>();
      for(Produto p : cliente.getCesta().getProdutos()) {
        Item item = new Item();
        item.setName(p.getNome().toString()).setQuantity("1").setCurrency("BRL").setPrice(String.valueOf(p.getPreco()));
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
      redirectUrls.setCancelUrl("http://localhost:8080/cart");
      redirectUrls.setReturnUrl("http://localhost:8080/paypal/checkout?cliente_id="+cliente.getId().toString()+"&guid="+guid);
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

  public String create_order(String transaction_id) {
    Integer cliente_id = Integer.valueOf(map.get("cliente_id"));
    Cliente cliente = (Cliente) clienteDao.findBy("id", cliente_id);

    Pedido pedido = new Pedido();
    pedidoDao.insert(pedido);

    pedido.setProdutos(new ArrayList<Produto>());
    Cesta cesta = cliente.getCesta();
    pedido.getProdutos().addAll(cesta.getProdutos());
    cesta.getProdutos().clear();
    cestaDao.update(cesta);

    pedido.setDataCompra(new java.util.Date());
    pedido.setMetodoPagamento(clazz.getSimpleName());
    pedido.setTransactionId(transaction_id);
    pedidoDao.update(pedido);

    if(cliente.getPedidos() == null) {
      cliente.setPedidos(new ArrayList<Pedido>());
      cliente.getPedidos().add(pedido);
      clienteDao.update(cliente);
    } else {
      cliente.getPedidos().add(pedido);
      clienteDao.update(cliente);
    }

    String returnUrl = "/pedido/" + pedido.getId().toString();
    try {
      mailSender.sendMessage("kleber-mota@uol.com.br", cliente.getUsuario().getEmail(), "Confirmação de pedido", "Seu pedido foi finalizado com sucesso. Você pode agora baixar os sistemas que comprou, acessando: http://localhost:8080"+returnUrl);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnUrl;
  }
}
