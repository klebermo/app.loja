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
import org.loja.model.usuario.Usuario;
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

  public String checkout(Integer usuario_id) {
    return create_order(usuario_id, UUID.randomUUID().toString());
  }

  public String create_order(Integer usuario_id, String transaction_id) {
    Usuario usuario = this.usuarioDao.findBy("id", usuario_id);
    return "/order/" + create_order(usuario, clazz.getSimpleName(), transaction_id);
  }

  public Preference preference(Usuario usuario) throws  MPException, MPConfException {
    String accessToken = ((MercadoPago) this.dao.get()).getAccessToken();
    com.mercadopago.MercadoPago.SDK.setAccessToken(accessToken);

    Preference preference = new Preference();

    Payer payer = new Payer();
    payer.setEmail(usuario.getEmail());
    preference.setPayer(payer);

    if(usuario.getCesta() != null)
      for(Produto p : usuario.getCesta().getProdutos()) {
        Item item = new Item();
        String nome = "";
        for(Titulo t : p.getNome())
          if(t.getIdioma().equals(httpServletRequest.getLocale().toString()))
            nome = t.getConteudo();
        item.setTitle(nome)
            .setQuantity(1)
            .setUnitPrice(p.getPreco());
        preference.appendItem(item);
      }

    preference.save();

    return preference;
  }
}
