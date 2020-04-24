package org.loja.model.cesta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;
import java.util.ArrayList;
import org.loja.model.cliente.Cliente;

@Controller
@RequestMapping("cart")
public class CestaController extends org.loja.model.Controller<Cesta> {
  public CestaController() {
    super(Cesta.class);
  }

  @RequestMapping("/index")
  public String cesta(Model model, @ModelAttribute("cliente") Cliente cliente) throws Exception {
    model.addAttribute("cart", "cart");

    if(cliente.getId() != null) {
      org.loja.settings.mercadopago.MercadoPagoService mercadoPagoServ = new org.loja.settings.mercadopago.MercadoPagoService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(mercadoPagoServ);
      com.mercadopago.resources.Preference mercadoPagoPreference = mercadoPagoServ.preference(cliente);
      model.addAttribute("mercadoPagoPreference", mercadoPagoPreference);
    }

    return "index";
  }
}
