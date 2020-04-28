package org.loja.settings.mercadopago;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.cliente.Cliente;
import org.loja.model.cliente.ClienteDao;
import org.loja.model.cesta.Cesta;
import org.loja.model.cesta.CestaDao;
import org.loja.model.pedido.Pedido;
import org.loja.model.pedido.PedidoDao;
import org.loja.model.titulo.Titulo;
import org.loja.model.produto.Produto;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.Preference.AutoReturn;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import org.loja.MailSender;

@Service
public class MercadoPagoService extends org.loja.settings.Service<MercadoPago> {
  @Autowired
  private ClienteDao clienteDao;

  @Autowired
  private CestaDao cestaDao;

  @Autowired
  private PedidoDao pedidoDao;

  @Autowired
  private MailSender mailSender;

  @Autowired
  private HttpServletRequest httpServletRequest;

  private Map<String, String> map = new HashMap<String, String>();

  public MercadoPagoService() {
    super(MercadoPago.class);
  }

  public String checkout(Integer cliente_id) {
    return create_order(UUID.randomUUID().toString().replaceAll("-", ""));
  }

  public Preference preference(Cliente cliente) throws  MPException, MPConfException {
    String accessToken = ((MercadoPago) this.dao.get()).getAccessToken();
    com.mercadopago.MercadoPago.SDK.setAccessToken(accessToken);

    Preference preference = new Preference();

    Payer payer = new Payer();
    payer.setEmail(cliente.getUsuario().getEmail());
    preference.setPayer(payer);
    preference.setAutoReturn(AutoReturn.approved);

    BackUrls backUrls = new BackUrls("https://localhost:8080/process_order", "http://localhost:8080/process_order", "http://localhost:8080/cart");
    preference.setBackUrls(backUrls);

    if(cliente.getCesta() != null) {
      if(cliente.getCesta().getProdutos().isEmpty()) {
        Item item = new Item();
        item.setTitle("...")
            .setQuantity(1)
            .setUnitPrice(1.f);
        preference.appendItem(item);
      } else {
        for(Produto p : cliente.getCesta().getProdutos()) {
          Item item = new Item();
          item.setTitle(p.getNome())
              .setQuantity(1)
              .setUnitPrice(p.getPreco());
          preference.appendItem(item);
        }
      }
    }

    preference.save();
    map.put("cliente_id", cliente.getId().toString());

    return preference;
  }

  public String create_order(String transaction_id) {
    Integer cliente_id = Integer.valueOf(map.get("cliente_id"));
    Cliente cliente = clienteDao.findBy("id", cliente_id);

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
    mailSender.sendMessage("kleber-mota@uol.com.br", cliente.getUsuario().getEmail(), "Confirmação de pedido", "Seu pedido foi finalizado com sucesso. Você pode agora baixar os sistemas que comprou, acessando: http://localhost:8080"+returnUrl);
    return returnUrl;
  }
}
