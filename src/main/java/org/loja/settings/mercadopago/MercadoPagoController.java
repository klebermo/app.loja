package org.loja.settings.mercadopago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("mercadopago")
public class MercadoPagoController extends org.loja.settings.Controller<MercadoPago> {
  @Autowired
  private MercadoPagoService serv;

  public MercadoPagoController() {
    super(MercadoPago.class);
  }

  @RequestMapping(value = "/checkout", method=RequestMethod.POST)
  public String checkout(@RequestParam("cliente_id") Integer cliente_id) {
    return "redirect:"+this.serv.checkout(cliente_id);
  }

  @RequestMapping(value = "/process_order", method=RequestMethod.GET)
  public String process_order(@RequestParam("cliente_id") Integer cliente_id, @RequestParam("transaction_id") String transaction_id) {
    return "redirect:"+this.serv.create_order(transaction_id);
  }
}
