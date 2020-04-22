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
  public String cesta(Model model, @RequestParam("cliente") Integer client_id) throws Exception {
    org.loja.model.cliente.Cliente result;
    try {
      org.loja.model.cliente.ClienteService clienteServ = new org.loja.model.cliente.ClienteService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(clienteServ);
      result = clienteServ.findBy("id", client_id);
    } catch (Exception e) {
      result = new org.loja.model.cliente.Cliente();
    }

    org.loja.settings.mercadopago.MercadoPagoService mercadoPagoServ = new org.loja.settings.mercadopago.MercadoPagoService();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(mercadoPagoServ);
    com.mercadopago.resources.Preference mercadoPagoPreference = mercadoPagoServ.preference(result);

    model.addAttribute("cart", "cart");
    model.addAttribute("mercadoPagoPreference", mercadoPagoPreference);
    return "index";
  }
}
