package org.loja.settings.mercadopago;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.mercadopago.resources.Preference;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.BackUrls;

import org.loja.model.titulo.Titulo;
import org.loja.model.cliente.Cliente;
import org.loja.model.produto.Produto;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class MercadoPagoService extends org.loja.settings.Service<MercadoPago> {
  @Autowired
  private HttpServletRequest httpServletRequest;

  public MercadoPagoService() {
    super(MercadoPago.class);
  }

  public String checkout(Integer cliente_id) {
    return create_order(cliente_id, UUID.randomUUID().toString());
  }

  public String create_order(Integer cliente_id, String transaction_id) {
    Cliente cliente = (Cliente) this.clienteDao.findBy("id", cliente_id);
    return "/order/" + create_order(cliente, clazz.getSimpleName(), transaction_id);
  }

  public Preference preference(Cliente cliente) throws  MPException, MPConfException {
    String accessToken = ((MercadoPago) this.dao.get()).getAccessToken();
    com.mercadopago.MercadoPago.SDK.setAccessToken(accessToken);

    Preference preference = new Preference();

    Payer payer = new Payer();
    payer.setEmail(cliente.getUsuario().getEmail());
    preference.setPayer(payer);

    if(cliente.getCesta() != null)
      for(Produto p : cliente.getCesta().getProdutos()) {
        Item item = new Item();
        item.setTitle(p.getNome())
            .setQuantity(1)
            .setUnitPrice(p.getPreco());
        preference.appendItem(item);
      }

    preference.save();

    return preference;
  }
}
