package org.loja.settings.pagseguro;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;

import org.loja.model.cliente.ClienteDao;
import org.loja.model.cliente.Cliente;
import org.loja.model.produto.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import javax.mail.MessagingException;

@Service
public class PagSeguroService extends org.loja.settings.Service<PagSeguro> {
  private Map<String, String> map = new HashMap<String, String>();

  public PagSeguroService() {
    super(PagSeguro.class);
  }

  public String checkout(Integer cliente_id) {
    Cliente cliente = clienteDao.findBy("id", cliente_id);
    map.put("cliente_id", cliente.getId().toString());

    String email = ((PagSeguro) this.dao.get()).getEmail();
    String token = ((PagSeguro) this.dao.get()).getToken();

    final br.com.uol.pagseguro.api.PagSeguro pagSeguro = br.com.uol.pagseguro.api.PagSeguro.instance(new SimpleLoggerFactory(), new JSEHttpClient(), Credential.sellerCredential(email, token), PagSeguroEnv.SANDBOX);

    CheckoutRegistrationBuilder registrationBuilder = new CheckoutRegistrationBuilder();
    registrationBuilder.withCurrency(Currency.BRL);
    for(Produto p : cliente.getCesta().getProdutos()) {
      PaymentItemBuilder item = new PaymentItemBuilder();
      item.withId(p.getId().toString());
      item.withDescription(p.getNome().toString());
      item.withAmount(new BigDecimal(p.getPreco()));
      item.withQuantity(1);
      registrationBuilder.addItem(item);
    }

    RegisteredCheckout registeredCheckout = pagSeguro.checkouts().register(registrationBuilder);

    return registeredCheckout.getRedirectURL();
  }

  public String create_order(String transaction_id) throws MessagingException {
    Integer cliente_id = Integer.valueOf(map.get("cliente_id"));
    Cliente cliente = this.clienteDao.findBy("id", cliente_id);
    return "/order/" + this.create_order(cliente, this.clazz.getSimpleName(), transaction_id);
  }
}
