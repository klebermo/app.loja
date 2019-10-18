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

import org.loja.model.usuario.UsuarioDao;
import org.loja.model.usuario.Usuario;
import org.loja.model.produto.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

@Service
public class PagSeguroService extends org.loja.settings.Service<PagSeguro> {
  private Map<String, String> map = new HashMap<String, String>();

  public PagSeguroService() {
    super(PagSeguro.class);
  }

  public String checkout(Integer usuario_id) {
    Usuario usuario = usuarioDao.findBy("id", usuario_id);
    map.put("usuario_id", usuario.getId().toString());

    String email = ((PagSeguro) this.dao.get()).getEmail();
    String token = ((PagSeguro) this.dao.get()).getToken();

    final br.com.uol.pagseguro.api.PagSeguro pagSeguro = br.com.uol.pagseguro.api.PagSeguro.instance(new SimpleLoggerFactory(), new JSEHttpClient(), Credential.sellerCredential(email, token), PagSeguroEnv.SANDBOX);

    CheckoutRegistrationBuilder registrationBuilder = new CheckoutRegistrationBuilder();
    registrationBuilder.withCurrency(Currency.BRL);
    for(Produto p : usuario.getCesta().getProdutos()) {
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

  public String create_order(String transaction_id) {
    Integer usuario_id = Integer.valueOf(map.get("usuario_id"));
    Usuario usuario = usuarioDao.findBy("id", usuario_id);
    return "/order/" + this.create_order(usuario, this.clazz.getSimpleName(), transaction_id);
  }
}
