package org.loja.model.cesta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("cart")
public class CestaController extends org.loja.model.Controller<Cesta> {
  public CestaController() {
    super(Cesta.class);
  }

  @RequestMapping("/index")
  public String cesta(Model model, @RequestParam(value="cliente", required=false) Integer cliente_id) throws Exception {
    model.addAttribute("cart", "cart");

    org.loja.model.cliente.Cliente result;
    if(cliente_id != null) {
      org.loja.model.cliente.ClienteService clienteServ = new org.loja.model.cliente.ClienteService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(clienteServ);
      result = clienteServ.findBy("id", cliente_id);

      org.loja.settings.mercadopago.MercadoPagoService mercadoPagoServ = new org.loja.settings.mercadopago.MercadoPagoService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(mercadoPagoServ);
      com.mercadopago.resources.Preference mercadoPagoPreference = mercadoPagoServ.preference(result);

      model.addAttribute("mercadoPagoPreference", mercadoPagoPreference);
    }

    return "index";
  }
}
