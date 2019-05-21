package org.loja.settings.mercadopago;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mercadopago")
public class MercadoPagoController extends org.loja.settings.Controller<MercadoPago> {
  public MercadoPagoController() {
    super(MercadoPago.class);
  }
}
