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
import org.loja.model.cliente.Cliente;
import org.loja.model.cliente.ClienteDao;
import org.loja.model.cesta.Cesta;
import org.loja.model.cesta.CestaDao;
import org.loja.model.pedido.Pedido;
import org.loja.model.pedido.PedidoDao;
import org.loja.model.produto.Produto;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class PagSeguroService extends org.loja.settings.Service<PagSeguro> {
  @Autowired
  private ClienteDao clienteDao;

  @Autowired
  private CestaDao cestaDao;

  @Autowired
  private PedidoDao pedidoDao;

  private Map<String, String> map = new HashMap<String, String>();

  public PagSeguroService() {
    super(PagSeguro.class);
  }

  public String checkout(Integer cliente_id) {
    Cliente cliente = (Cliente) clienteDao.findBy("id", cliente_id);
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

  public String create_order(String transaction_id) {
    Integer cliente_id = Integer.valueOf(map.get("cliente_id"));
    Cliente cliente = (Cliente) this.clienteDao.findBy("id", cliente_id);

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

    return "/pedido/" + pedido.getId().toString();
  }
}
