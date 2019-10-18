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

import org.loja.model.usuario.UsuarioDao;
import org.loja.model.usuario.Usuario;
import org.loja.model.produto.Produto;

import java.util.Iterator;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Service
public class PaypalService extends org.loja.settings.Service<Paypal> {
  @Autowired
  private UsuarioDao usuarioDao;

  private Map<String, String> map = new HashMap<String, String>();

  public PaypalService() {
    super(Paypal.class);
  }

  public String checkout(Integer usuario_id, String payerId, String guid) throws PayPalRESTException {
    Usuario usuario = usuarioDao.findBy("id", usuario_id);
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
      return this.create_order(usuario.getId(), createdPayment.getId());
    } else {
      Float total = 0.f;
      for(Produto produto : usuario.getCesta().getProdutos())
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
      for(Produto p : usuario.getCesta().getProdutos()) {
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
}
