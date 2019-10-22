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
  public String checkout(@RequestParam("usuario_id") Integer usuario_id) {
    return "redirect:"+this.serv.checkout(usuario_id);
  }

  @RequestMapping(value = "/process_order", method=RequestMethod.GET)
  public String process_order(@RequestParam("usuario_id") Integer usuario_id, @RequestParam("transaction_id") String transaction_id) {
    return "redirect:"+this.serv.create_order(usuario_id, transaction_id);
  }
}
